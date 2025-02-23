package org.example.grandaobiblioteca.servicio;


import org.example.grandaobiblioteca.entidad.Libro;
import org.example.grandaobiblioteca.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepository libroRepo;

    public boolean addLibro(Libro libro){
        try {
            libroRepo.save(libro);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteLibro(String isbn){
        try{
            libroRepo.deleteById(isbn);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Boolean updateLibro(Libro libro, String isbn) {
        try {
            // Buscar el libro por el ISBN proporcionado
            Optional<Libro> libroExistente = libroRepo.findById(isbn);

            if (libroExistente.isPresent()) {
                // Obtener el libro existente
                Libro existingLibro = libroExistente.get();

                // No modificar el ISBN
                existingLibro.setAutor(libro.getAutor());
                existingLibro.setTitulo(libro.getTitulo());
                existingLibro.setEjemplars(libro.getEjemplars());

                // Guardar los cambios en el repositorio
                libroRepo.save(existingLibro);
                return true;
            } else {
                // Si no se encuentra el libro, devolver false
                System.out.println("Libro con ISBN " + isbn + " no encontrado.");
                return false;
            }
        } catch (Exception e) {
            // Registrar la excepción para depuración
            System.err.println("Error al actualizar el libro: " + e.getMessage());
            return false;
        }
    }


    public ResponseEntity<Libro> findLibro(String isbn){
        Libro libro = libroRepo.findById(isbn).get();
        return ResponseEntity.ok(libro);


    }
    public ResponseEntity<List<Libro>> findALL(){
        List<Libro> libros = libroRepo.findAll();
        return ResponseEntity.ok(libros);
    }

    public String findALLText(){
        List<Libro> libros = this.findALL().getBody();
        StringBuilder texto = new StringBuilder();
        for (Libro libro : libros) {
            texto.append(libro.toString());
        }
        return texto.toString();
    }

    public String findByLibroTexto(String isbn){
        Libro libro = this.findLibro(isbn).getBody();
        return libro.toString();
    }

    public String addLibroText(String texto){
        String[] linea = texto.split(",");
        Libro libro = new Libro(linea[0], linea[1], linea[2]);
        boolean valor = this.addLibro(libro);
        return valor == true ? "Libro añadido" : "Error al añadir el libro";
    }
    public String updateLibroText(String texto, String isbn){
        String[] linea = texto.split(",");
        Libro libro = new Libro(linea[0], linea[1], linea[2]);
        boolean valor = this.updateLibro(libro, isbn);
        return valor == true ? "Libro actualizado" : "Error al actualizar el libro";
    }



}