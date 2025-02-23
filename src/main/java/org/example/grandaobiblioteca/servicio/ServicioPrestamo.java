package org.example.grandaobiblioteca.servicio;


import org.example.grandaobiblioteca.entidad.Prestamo;
import org.example.grandaobiblioteca.entidad.PrestamoMongo;
import org.example.grandaobiblioteca.repositorio.PrestamoMongoRepo;
import org.example.grandaobiblioteca.repositorio.PrestamoRepository;
import org.example.grandaobiblioteca.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioPrestamo {

    @Autowired
    private PrestamoRepository prestamoRepo;
    @Autowired
    private PrestamoMongoRepo mongo;

    public boolean addPrestamo(Prestamo prestamo){
        try {
            prestamoRepo.save(prestamo);
            mongo.save(new PrestamoMongo(prestamo));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean deletePrestamo(Integer id){
        try{
            prestamoRepo.deleteById(id);
            mongo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Boolean updatePrestamo(Prestamo prestamo) {
        try {
            // Buscar el prestamo por el ISBN proporcionado
            Optional<Prestamo> prestamoExistente = prestamoRepo.findById(prestamo.getId());

            if (prestamoExistente.isPresent()) {
                //no hace falta existingE es igual a prestamo
                /* Obtener el prestamo existente
                Prestamo existingPrestamo = prestamoExistente.get();

                // No modificar el ISBN
                existingPrestamo.setAutor(prestamo.getAutor());
                existingPrestamo.setTitulo(prestamo.getTitulo());
                existingPrestamo.setPrestamos(prestamo.getPrestamos());*/

                // Guardar los cambios en el repositorio
                prestamoRepo.save(prestamo);
                mongo.save(new PrestamoMongo(prestamo));
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


    public ResponseEntity<Prestamo> findPrestamo(Integer id){
        Prestamo prestamo = prestamoRepo.findById(id).get();
        return ResponseEntity.ok(prestamo);
    }
    public ResponseEntity<List<Prestamo>> findALL(){
        List<Prestamo> prestamos = prestamoRepo.findAll();
        return ResponseEntity.ok(prestamos);
    }

    public String findALLText(){
        List<Prestamo> prestamos = this.findALL().getBody();
        StringBuilder texto = new StringBuilder();
        for (Prestamo prestamo : prestamos) {
            texto.append(prestamo.toString());
        }
        return texto.toString();
    }

}
