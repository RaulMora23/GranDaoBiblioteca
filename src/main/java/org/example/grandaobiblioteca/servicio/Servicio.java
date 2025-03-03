package org.example.grandaobiblioteca.servicio;

import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBException;
import org.example.grandaobiblioteca.entidad.*;
import org.example.grandaobiblioteca.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class Servicio {
    
    @Autowired
    EjemplarRepository ejemplarRepository;
    @Autowired
    EjemplarMongoRepo ejemplarMongoRepo;
    @Autowired
    LibroMongoRepo libroMongoRepo;
    @Autowired
    PrestamoRepository prestamoRepository;
    @Autowired
    PrestamoMongoRepo prestamoMongoRepo;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioMongoRepo usuarioMongoRepo;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private XMLLibro xmlLibro;
    @Autowired
    private TXTLibro txtLibro;

    @PostConstruct//Para métodos init()
    public void init() throws IOException, JAXBException {
        txtLibro.persistir(libroRepository.findAll());
        xmlLibro.persistit(libroRepository.findAll());
    }


    //EJEMPLAR
    
    public boolean validarEjemplar(Ejemplar ejemplar) {
        if (ejemplar.getEstado().equals("Disponible") && ejemplar.getEstado().equals("Dañado") && ejemplar.getEstado().equals("Prestado")) {
            return false;
        }
        String isbn = ejemplar.getIsbn().getIsbn();
        isbn = isbn.replaceAll("-", "");
        int i = 1;
        int valor = 0;
        try {
            for (char caracter : isbn.toCharArray()) {
                if (i == 13) {
                    valor = valor + Integer.parseInt(String.valueOf(caracter));
                    if (valor % 10 == 0) {
                        return true;
                    }
                } else if (i % 2 == 1) {
                    valor = valor + Integer.parseInt(String.valueOf(caracter));
                } else {
                    valor = valor + Integer.parseInt(String.valueOf(caracter)) * 3;
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println("Isbn no valido");
            e.printStackTrace();
        }
        return false;
    }


    public boolean addEjemplar(Ejemplar ejemplar){
        try {
            ejemplarRepository.save(ejemplar);
            ejemplarMongoRepo.save(new EjemplarMongo(ejemplar));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteEjemplar(Integer id){
        try{
            ejemplarRepository.deleteById(id);
            ejemplarMongoRepo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Boolean updateEjemplar(Ejemplar ejemplar) {
        try {
            // Buscar el ejemplar por el ISBN proporcionado
            Optional<Ejemplar> ejemplarExistente = ejemplarRepository.findById(ejemplar.getId());

            if (ejemplarExistente.isPresent()) {
                //no hace falta existingE es igual a ejemplar
                /* Obtener el ejemplar existente
                Ejemplar existingEjemplar = ejemplarExistente.get();

                // No modificar el ISBN
                existingEjemplar.setAutor(ejemplar.getAutor());
                existingEjemplar.setTitulo(ejemplar.getTitulo());
                existingEjemplar.setEjemplars(ejemplar.getEjemplars());*/

                // Guardar los cambios en el repositorio
                ejemplarRepository.save(ejemplar);
                ejemplarMongoRepo.save(new EjemplarMongo(ejemplar));
                return true;
            } else {
                // Si no se encuentra el ejemplar, devolver false
                //System.out.println("Ejemplar con ISBN " + isbn + " no encontrado.");
                return false;
            }
        } catch (Exception e) {
            // Registrar la excepción para depuración
            System.err.println("Error al actualizar el ejemplar: " + e.getMessage());
            return false;
        }
    }

    public String addEjemplarText(String texto){
        String[] linea = texto.split(",");
        Ejemplar ejemplar = new Ejemplar(Integer.parseInt(linea[0]),libroRepository.findById(linea[1]).get(), linea[2]);
        if(!validarEjemplar(ejemplar)){
            return "Ejemplar no valido";
        }
        boolean valor = this.addEjemplar(ejemplar);
        return valor == true ? "Ejemplar añadido" : "Error al añadir el ejemplar";
    }
    public String updateEjemplarText(String texto){
        String[] linea = texto.split(",");
        Ejemplar ejemplar = new Ejemplar(Integer.parseInt(linea[0]),libroRepository.findById(linea[1]).get(), linea[2]);
        if(!validarEjemplar(ejemplar)){
            return "Ejemplar no valido";
        }
        boolean valor = this.updateEjemplar(ejemplar);
        return valor == true ? "Ejemplar actualizado" : "Error al actualizar el ejemplar";
    }

    public ResponseEntity<Ejemplar> findEjemplar(Integer id){
        Ejemplar ejemplar = ejemplarRepository.findById(id).get();
        return ResponseEntity.ok(ejemplar);
    }
    public ResponseEntity<List<Ejemplar>> findALLEjemplar(){
        List<Ejemplar> ejemplars = ejemplarRepository.findAll();
        return ResponseEntity.ok(ejemplars);
    }

    public String findALLTextEjemplar(){
        List<Ejemplar> ejemplars = findALLEjemplar().getBody();
        StringBuilder texto = new StringBuilder();
        for (Ejemplar ejemplar : ejemplars) {
            texto.append(ejemplar.toString());
        }
        return texto.toString();
    }
    
    //LIBRO
    public boolean validarLibro(Libro ejemplar) {

        String isbn = ejemplar.getIsbn();
        isbn = isbn.replaceAll("-", "");
        int i = 1;
        int valor = 0;
        try {
            for (char caracter : isbn.toCharArray()) {
                if (i == 13) {
                    valor = valor + Integer.parseInt(String.valueOf(caracter));
                    if (valor % 10 == 0) {
                        return true;
                    }
                } else if (i % 2 == 1) {
                    valor = valor + Integer.parseInt(String.valueOf(caracter));
                } else {
                    valor = valor + Integer.parseInt(String.valueOf(caracter)) * 3;
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println("Isbn no valido");
            e.printStackTrace();
        }
        return false;
    }

    public boolean addLibro(Libro libro){
        try {
            libroMongoRepo.save(new LibroMongo(libro));
            libroRepository.save(libro);
            xmlLibro.guardarLibro(libro);
            txtLibro.agregarLibro(libro);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteLibro(String isbn){
        try{
            libroMongoRepo.deleteById(isbn);
            libroRepository.deleteById(isbn);
            xmlLibro.eliminarLibro(isbn);
            txtLibro.eliminarLibro(isbn);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Boolean updateLibro(Libro libro) {
        try {
            // Buscar el libro por el ISBN proporcionado
            Optional<Libro> libroExistente = libroRepository.findById(libro.getIsbn());

            if (libroExistente.isPresent()) {
                // Obtener el libro existente
                /*Libro existingLibro = libroExistente.get();

                // No modificar el ISBN
                existingLibro.setAutor(libro.getAutor());
                existingLibro.setTitulo(libro.getTitulo());
                existingLibro.setEjemplars(libro.getEjemplars());*/

                // Guardar los cambios en el repositorio
                libroMongoRepo.save(new LibroMongo(libro));
                libroRepository.save(libro);
                xmlLibro.modificarLibro(libro);
                txtLibro.actualizarLibro(libro);
                return true;
            } else {
                // Si no se encuentra el libro, devolver false
                System.out.println("Libro con ISBN " + libro.getIsbn() + " no encontrado.");
                return false;
            }
        } catch (Exception e) {
            // Registrar la excepción para depuración
            System.err.println("Error al actualizar el libro: " + e.getMessage());
            return false;
        }
    }
    public String addLibroText(String texto){
        String[] linea = texto.split(",");
        Libro libro = new Libro(linea[0], linea[1], linea[2]);
        if(!validarLibro(libro)){
            return "Libro no valido";
        }
        boolean valor = this.addLibro(libro);
        return valor == true ? "Libro añadido" : "Error al añadir el libro";
    }
    public String updateLibroText(String texto){
        String[] linea = texto.split(",");
        Libro libro = new Libro(linea[0], linea[1], linea[2]);
        if(!validarLibro(libro)){
            return "Libro no valido";
        }
        boolean valor = this.updateLibro(libro);
        return valor == true ? "Libro actualizado" : "Error al actualizar el libro";
    }

    public ResponseEntity<Libro> findLibro(String isbn){
        Libro libro = libroRepository.getById(isbn);
        return ResponseEntity.ok(libro);


    }
    public ResponseEntity<List<Libro>> findALLLibro(){
        List<Libro> libros = libroRepository.findAll();
        return ResponseEntity.ok(libros);
    }

    public String findALLTextLibro(){
        List<Libro> libros = this.findALLLibro().getBody();
        StringBuilder texto = new StringBuilder();
        for (Libro libro : libros) {
            texto.append(libro.toString());
        }
        return texto.toString();
    }

    //PRÉSTAMO

    public boolean validarPrestamo(Prestamo prestamo){
        try {
            if (!usuarioRepository.getById(prestamo.getUsuario().getId()).getPenalizacionHasta().isAfter(LocalDate.now())) {
                if (ejemplarRepository.getById(prestamo.getEjemplar().getId()).getEstado().equals("Disponible")) {
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println("Prestamo no valido");
            e.printStackTrace();
        }
        return false;
    }

    public boolean addPrestamo(Prestamo prestamo){
        try {

            prestamoRepository.save(prestamo);
            prestamoMongoRepo.save(new PrestamoMongo(prestamo));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean deletePrestamo(Integer id){
        try{
            prestamoRepository.deleteById(id);
            prestamoMongoRepo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Boolean updatePrestamo(Prestamo prestamo) {
        try {
            // Buscar el prestamo por el ISBN proporcionado
            Optional<Prestamo> prestamoExistente = prestamoRepository.findById(prestamo.getId());

            if (prestamoExistente.isPresent()) {
                //no hace falta existingE es igual a prestamo
                /* Obtener el prestamo existente
                Prestamo existingPrestamo = prestamoExistente.get();

                // No modificar el ISBN
                existingPrestamo.setAutor(prestamo.getAutor());
                existingPrestamo.setTitulo(prestamo.getTitulo());
                existingPrestamo.setPrestamos(prestamo.getPrestamos());*/

                // Guardar los cambios en el repositorio
                prestamoRepository.save(prestamo);
                prestamoMongoRepo.save(new PrestamoMongo(prestamo));
                return true;
            } else {
                // Si no se encuentra el prestamo, devolver false
                //System.out.println("Prestamo con ISBN " + isbn + " no encontrado.");
                return false;
            }
        } catch (Exception e) {
            // Registrar la excepción para depuración
            System.err.println("Error al actualizar el prestamo: " + e.getMessage());
            return false;
        }
    }

    public String addPrestamoText(String texto){
        String[] linea = texto.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de la fecha

        // Convierte el String a LocalDate
        LocalDate inicio = LocalDate.parse(linea[3], formatter);
        LocalDate fin = LocalDate.parse(linea[4], formatter);

        Prestamo prestamo = new Prestamo(Integer.parseInt(linea[0]), usuarioRepository.getUsuarioById(Integer.parseInt(linea[1])), libroRepository.getLibroByIsbn(linea[2]),inicio,fin );
        if(!validarPrestamo(prestamo)){
            return "Prestamo no valido";
        }
        boolean valor = this.addPrestamo(prestamo);
        return valor == true ? "Prestamo añadido" : "Error al añadir el prestamo";
    }
    public String updatePrestamoText(String texto){
        String[] linea = texto.split(",");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de la fecha

        // Convierte el String a LocalDate
        LocalDate inicio = LocalDate.parse(linea[3], formatter);
        LocalDate fin = LocalDate.parse(linea[4], formatter);

        Prestamo prestamo = new Prestamo(Integer.parseInt(linea[0]), usuarioRepository.getUsuarioById(Integer.parseInt(linea[1])), libroRepository.getLibroByIsbn(linea[2]),inicio,fin );
        if(!validarPrestamo(prestamo)){
            return "Prestamo no valido";
        }
        boolean valor = this.updatePrestamo(prestamo);
        return valor == true ? "Prestamo actualizado" : "Error al actualizar el prestamo";
    }

    public ResponseEntity<Prestamo> findPrestamo(Integer id){
        Prestamo prestamo = prestamoRepository.findById(id).get();
        return ResponseEntity.ok(prestamo);
    }
    public ResponseEntity<List<Prestamo>> findALLPrestamo(){
        List<Prestamo> prestamos = prestamoRepository.findAll();
        return ResponseEntity.ok(prestamos);
    }

    public String findALLTextPrestamo(){
        List<Prestamo> prestamos = this.findALLPrestamo().getBody();
        StringBuilder texto = new StringBuilder();
        for (Prestamo prestamo : prestamos) {
            texto.append(prestamo.toString());
        }
        return texto.toString();
    }
    //USUARIO

    public boolean addUsuario(Usuario usuario){
        try {
            usuarioRepository.save(usuario);
            usuarioMongoRepo.save(new UsuarioMongo(usuario));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteUsuario(Integer id){
        try{
            usuarioRepository.deleteById(id);
            usuarioMongoRepo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Boolean updateUsuario(Usuario usuario) {
        try {
            // Buscar el usuario por el ISBN proporcionado
            Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getId());

            if (usuarioExistente.isPresent()) {
                //no hace falta existingE es igual a usuario
                /* Obtener el usuario existente
                Usuario existingUsuario = usuarioExistente.get();

                // No modificar el ISBN
                existingUsuario.setAutor(usuario.getAutor());
                existingUsuario.setTitulo(usuario.getTitulo());
                existingUsuario.setUsuarios(usuario.getUsuarios());*/

                // Guardar los cambios en el repositorio
                usuarioRepository.save(usuario);
                usuarioMongoRepo.save(new UsuarioMongo(usuario));
                return true;
            } else {
                // Si no se encuentra el usuario, devolver false
                //System.out.println("Usuario con ISBN " + isbn + " no encontrado.");
                return false;
            }
        } catch (Exception e) {
            // Registrar la excepción para depuración
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }

    public String addUsuarioText(String texto){
        String[] linea = texto.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de la fecha
        LocalDate fecha = LocalDate.parse(linea[6], formatter);
        Usuario usuario = new Usuario(Integer.parseInt(linea[0]),linea[1],linea[2],linea[3],linea[4],linea[5],fecha);
        boolean valor = this.addUsuario(usuario);
        return valor == true ? "Usuario añadido" : "Error al añadir el usuario";
    }
    public String updateUsuarioText(String texto){
        String[] linea = texto.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de la fecha
        LocalDate fecha = LocalDate.parse(linea[6], formatter);
        Usuario usuario = new Usuario(Integer.parseInt(linea[0]),linea[1],linea[2],linea[3],linea[4],linea[5],fecha);
        boolean valor = this.updateUsuario(usuario);
        return valor == true ? "Usuario actualizado" : "Error al actualizar el usuario";
    }

    public ResponseEntity<Usuario> findUsuario(Integer id){
        Usuario usuario = usuarioRepository.findById(id).get();
        return ResponseEntity.ok(usuario);
    }
    public ResponseEntity<List<Usuario>> findALLUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    public String findALLText(){
        List<Usuario> usuarios = findALLUsuario().getBody();
        StringBuilder texto = new StringBuilder();
        for (Usuario usuario : usuarios) {
            texto.append(usuario.toString());
        }
        return texto.toString();
    }
}
