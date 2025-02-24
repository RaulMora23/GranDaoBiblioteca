package org.example.grandaobiblioteca.servicio;


import org.example.grandaobiblioteca.entidad.Prestamo;
import org.example.grandaobiblioteca.entidad.PrestamoMongo;
import org.example.grandaobiblioteca.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServicio {

    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private PrestamoMongoRepo prestamoMongoRepo;
    @Autowired
    private EjemplarRepository ejemplarRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LibroRepository libroRepository;

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
}
