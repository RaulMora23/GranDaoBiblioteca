package org.example.grandaobiblioteca.servicio;


import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.example.grandaobiblioteca.entidad.EjemplarMongo;
import org.example.grandaobiblioteca.repositorio.EjemplarMongoRepo;
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
    private EjemplarMongoRepo mongo;
    @Autowired
    private LibroRepository libroRepo;

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
            ejemplarRepo.save(ejemplar);
            mongo.save(new EjemplarMongo(ejemplar));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteEjemplar(Integer id){
        try{
            ejemplarRepo.deleteById(id);
            mongo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
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
        Ejemplar ejemplar = new Ejemplar(Integer.parseInt(linea[0]),libroRepo.findById(linea[1]).get(), linea[2]);
        if(!validarEjemplar(ejemplar)){
            return "Ejemplar no valido";
        }
        boolean valor = this.addEjemplar(ejemplar);
        return valor == true ? "Ejemplar añadido" : "Error al añadir el ejemplar";
    }
    public String updateEjemplarText(String texto){
        String[] linea = texto.split(",");
        Ejemplar ejemplar = new Ejemplar(Integer.parseInt(linea[0]),libroRepo.findById(linea[1]).get(), linea[2]);
        if(!validarEjemplar(ejemplar)){
            return "Ejemplar no valido";
        }
        boolean valor = this.updateEjemplar(ejemplar);
        return valor == true ? "Ejemplar actualizado" : "Error al actualizar el ejemplar";
    }

    public ResponseEntity<Ejemplar> findEjemplar(Integer id){
        Ejemplar ejemplar = ejemplarRepo.findById(id).get();
        return ResponseEntity.ok(ejemplar);
    }
    public ResponseEntity<List<Ejemplar>> findALL(){
        List<Ejemplar> ejemplars = ejemplarRepo.findAll();
        return ResponseEntity.ok(ejemplars);
    }

    public String findALLTextEjemplar(){
        List<Ejemplar> ejemplars = this.findALL().getBody();
        StringBuilder texto = new StringBuilder();
        for (Ejemplar ejemplar : ejemplars) {
            texto.append(ejemplar.toString());
        }
        return texto.toString();
    }
}
