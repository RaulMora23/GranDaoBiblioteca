package org.example.grandaobiblioteca.servicio;

<<<<<<< Updated upstream
import org.example.grandaobiblioteca.entidad.Ejemplar;

import org.example.grandaobiblioteca.repositorio.EjemplarRepository;

=======

import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.example.grandaobiblioteca.entidad.EjemplarMongo;
import org.example.grandaobiblioteca.repositorio.EjemplarMongoRepo;
import org.example.grandaobiblioteca.repositorio.EjemplarRepository;
>>>>>>> Stashed changes
import org.example.grandaobiblioteca.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@Service
public class EjemplarServicio {

    @Autowired
    private EjemplarRepository ejemplarRepo;

<<<<<<< Updated upstream
    @Autowired
    private LibroRepository libroRepository;

    public boolean addEjemplar(Ejemplar Ejemplar){
        try {
            ejemplarRepo.save(Ejemplar);
=======
    private EjemplarMongoRepo mongo;

    @Autowired
    private LibroRepository libroRepo;
    public boolean addEjemplar(Ejemplar ejemplar){
        try {
            ejemplarRepo.save(ejemplar);
            mongo.save(new EjemplarMongo(ejemplar));
>>>>>>> Stashed changes
            return true;
        } catch (Exception e) {
            return false;
        }
    }
<<<<<<< Updated upstream
    public boolean deleteEjemplar(int id){
        try{
            ejemplarRepo.deleteById(id);
=======
    public boolean deleteEjemplar(Integer id){
        try{
            ejemplarRepo.deleteById(id);
            mongo.deleteById(id);
>>>>>>> Stashed changes
            return true;
        } catch (Exception e){
            return false;
        }
    }
<<<<<<< Updated upstream
    public Boolean updateEjemplar(Ejemplar Ejemplar, int id) {
        try {
            // Buscar el libro por el ISBN proporcionado
            Optional<Ejemplar> ejemplarExistente = ejemplarRepo.findById(id);

            if (ejemplarExistente.isPresent()) {
                // Obtener el libro existente
                Ejemplar existingEjemplar = ejemplarExistente.get();

                // No modificar el ISBN
                existingEjemplar.setIsbn(Ejemplar.getIsbn());
                existingEjemplar.setEstado(Ejemplar.getEstado());

                // Guardar los cambios en el repositorio
                ejemplarRepo.save(existingEjemplar);
                return true;
            } else {
                // Si no se encuentra el Ejemplar, devolver false
                System.out.println("Ejemplar con ISBN " + id + " no encontrado.");
=======
    public Boolean updateEjemplar(Ejemplar ejemplar) {
        try {
            // Buscar el ejemplar por el ISBN proporcionado
            Optional<Ejemplar> ejemplarExistente = ejemplarRepo.findById(ejemplar.getId());

            if (ejemplarExistente.isPresent()) {
                //no hace falta existingE es igual a ejemplar
                /* Obtener el ejemplar existente
                Ejemplar existingEjemplar = ejemplarExistente.get();

                // No modificar el ISBN
                existingEjemplar.setAutor(ejemplar.getAutor());
                existingEjemplar.setTitulo(ejemplar.getTitulo());
                existingEjemplar.setEjemplars(ejemplar.getEjemplars());*/

                // Guardar los cambios en el repositorio
                ejemplarRepo.save(ejemplar);
                mongo.save(new EjemplarMongo(ejemplar));
                return true;
            } else {
                // Si no se encuentra el ejemplar, devolver false
                //System.out.println("Ejemplar con ISBN " + isbn + " no encontrado.");
>>>>>>> Stashed changes
                return false;
            }
        } catch (Exception e) {
            // Registrar la excepción para depuración
<<<<<<< Updated upstream
            System.err.println("Error al actualizar el Ejemplar: " + e.getMessage());
            return false;
        }
    }


    public ResponseEntity<Ejemplar> findEjemplar(int id){
        Ejemplar Ejemplar = ejemplarRepo.findById(id).get();
        return ResponseEntity.ok(Ejemplar);


    }
    public ResponseEntity<List<Ejemplar>> findALL(){
        List<Ejemplar> Ejemplars = ejemplarRepo.findAll();
        return ResponseEntity.ok(Ejemplars);
    }

    public String findALLText(){
        List<Ejemplar> Ejemplars = this.findALL().getBody();
        StringBuilder texto = new StringBuilder();
        for (Ejemplar Ejemplar : Ejemplars) {
            texto.append(Ejemplar.toString());
        }
        return texto.toString();
    }

    public String findByEjemplarTexto(int id){
        Ejemplar Ejemplar = this.findEjemplar(id).getBody();
        return Ejemplar.toString();
    }

    public String addEjemplarText(String texto){
        String[] linea = texto.split(",");
        Ejemplar Ejemplar = new Ejemplar(libroRepository.findById(linea[0]).get(), linea[1]);
        boolean valor = this.addEjemplar(Ejemplar);
        return valor == true ? "Ejemplar añadido" : "Error al añadir el Ejemplar";
    }
    public String updateEjemplarText(String texto, int id){
        String[] linea = texto.split(",");
        Ejemplar Ejemplar = new Ejemplar(libroRepository.findById(linea[0]).get(), linea[1]);
        boolean valor = this.updateEjemplar(Ejemplar, id);
        return valor == true ? "Ejemplar actualizado" : "Error al actualizar el Ejemplar";
    }

=======
            System.err.println("Error al actualizar el ejemplar: " + e.getMessage());
            return false;
        }
    }
>>>>>>> Stashed changes


    public ResponseEntity<Ejemplar> findEjemplar(Integer id){
        Ejemplar ejemplar = ejemplarRepo.findById(id).get();
        return ResponseEntity.ok(ejemplar);
    }
    public ResponseEntity<List<Ejemplar>> findALL(){
        List<Ejemplar> ejemplars = ejemplarRepo.findAll();
        return ResponseEntity.ok(ejemplars);
    }

    public String findALLText(){
        List<Ejemplar> ejemplars = this.findALL().getBody();
        StringBuilder texto = new StringBuilder();
        for (Ejemplar ejemplar : ejemplars) {
            texto.append(ejemplar.toString());
        }
        return texto.toString();
    }


    public String addEjemplarText(String texto){
        String[] linea = texto.split(",");
        Ejemplar ejemplar = new Ejemplar(Integer.parseInt(linea[0]),libroRepo.findById(linea[1]).get(), linea[2]);
        boolean valor = this.addEjemplar(ejemplar);
        return valor == true ? "Ejemplar añadido" : "Error al añadir el ejemplar";
    }
    public String updateEjemplarText(String texto){
        String[] linea = texto.split(",");
        Ejemplar ejemplar = new Ejemplar(Integer.parseInt(linea[0]),libroRepo.findById(linea[1]).get(), linea[2]);
        boolean valor = this.updateEjemplar(ejemplar);
        return valor == true ? "Ejemplar actualizado" : "Error al actualizar el ejemplar";
    }
}
