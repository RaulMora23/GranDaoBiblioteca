package org.example.grandaobiblioteca.servicio;

import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.example.grandaobiblioteca.entidad.EjemplarMongo;
import org.example.grandaobiblioteca.entidad.Libro;
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

    public boolean addEjemplar(Ejemplar ejemplar) {
        try {
            ejemplarRepo.save(ejemplar);
            mongo.save(new EjemplarMongo(ejemplar));
            return true;
        } catch (Exception e) {
            System.err.println("Error al añadir ejemplar: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteEjemplar(int id) {
        try {
            ejemplarRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar ejemplar: " + e.getMessage());
            return false;
        }
    }

    public boolean updateEjemplar(Ejemplar ejemplar) {
        try {
            Optional<Ejemplar> ejemplarExistente = ejemplarRepo.findById(ejemplar.getId());
            if (ejemplarExistente.isPresent()) {
                ejemplarRepo.save(ejemplar);
                mongo.save(new EjemplarMongo(ejemplar));
                return true;
            } else {
                System.err.println("Ejemplar con ID " + ejemplar.getId() + " no encontrado.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar ejemplar: " + e.getMessage());
            return false;
        }
    }

    public ResponseEntity<Ejemplar> findEjemplar(int id) {
        Optional<Ejemplar> ejemplar = ejemplarRepo.findById(id);
        return ejemplar.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Ejemplar>> findALL() {
        List<Ejemplar> ejemplares = ejemplarRepo.findAll();
        return ResponseEntity.ok(ejemplares);
    }

    public String findALLText() {
        List<Ejemplar> ejemplares = findALL().getBody();
        if (ejemplares == null || ejemplares.isEmpty()) return "No hay ejemplares disponibles.";
        StringBuilder texto = new StringBuilder();
        for (Ejemplar ejemplar : ejemplares) {
            texto.append(ejemplar.toString()).append("\n");
        }
        return texto.toString();
    }

    public String findByEjemplarTexto(int id) {
        Optional<Ejemplar> ejemplar = ejemplarRepo.findById(id);
        return ejemplar.map(Ejemplar::toString)
                .orElse("Ejemplar no encontrado.");
    }

    public String addEjemplarText(String texto) {
        try {
            String[] linea = texto.split(",");
            Optional<Libro> libroOpt = libroRepo.findById(linea[0]);
            if (libroOpt.isPresent()) {
                Ejemplar ejemplar = new Ejemplar(libroOpt.get(), linea[1]);
                boolean resultado = addEjemplar(ejemplar);
                return resultado ? "Ejemplar añadido correctamente" : "Error al añadir el ejemplar";
            } else {
                return "Error: Libro no encontrado";
            }
        } catch (Exception e) {
            return "Error en la entrada de datos: " + e.getMessage();
        }
    }

    public String updateEjemplarText(String texto) {
        try {
            String[] linea = texto.split(",");
            Optional<Libro> libroOpt = libroRepo.findById(linea[0]);
            if (libroOpt.isPresent()) {
                Ejemplar ejemplar = new Ejemplar(Integer.parseInt(linea[1]), libroOpt.get(), linea[2]);
                boolean resultado = updateEjemplar(ejemplar);
                return resultado ? "Ejemplar actualizado correctamente" : "Error al actualizar el ejemplar";
            } else {
                return "Error: Libro no encontrado";
            }
        } catch (Exception e) {
            return "Error en la entrada de datos: " + e.getMessage();
        }
    }
}
