package org.example.grandaobiblioteca.servicio;

import org.example.grandaobiblioteca.entidad.Ejemplar;

import org.example.grandaobiblioteca.repositorio.EjemplarRepository;

import org.example.grandaobiblioteca.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EjemplarServicio {

    @Autowired
    private EjemplarRepository ejemplarRepo;

    @Autowired
    private LibroRepository libroRepository;

    public boolean addEjemplar(Ejemplar Ejemplar){
        try {
            ejemplarRepo.save(Ejemplar);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteEjemplar(int id){
        try{
            ejemplarRepo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
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
                return false;
            }
        } catch (Exception e) {
            // Registrar la excepción para depuración
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



}
