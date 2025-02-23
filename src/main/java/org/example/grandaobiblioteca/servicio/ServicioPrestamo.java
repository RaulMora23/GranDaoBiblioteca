package org.example.grandaobiblioteca.servicio;
import org.example.grandaobiblioteca.entidad.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.grandaobiblioteca.repositorio.EjemplarRepository;
import org.example.grandaobiblioteca.repositorio.PrestamoMongoRepo;
import org.example.grandaobiblioteca.repositorio.PrestamoRepository;
import org.example.grandaobiblioteca.repositorio.UsuarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPrestamo {

    @Autowired
    PrestamoRepository repo;
    @Autowired
    PrestamoMongoRepo mongo;
    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    EjemplarRepository ejemplarRepo;
    
    public boolean validarPrestamo(Prestamo prestamoDto){
        try {
            if (usuarioRepo.findById(prestamoDto.getUsuario().getId()).get().getPenalizacionHasta().isAfter(LocalDate.now())) {
                if (ejemplarRepo.findById(prestamoDto.getEjemplar().getId()).get().getEstado().equals("Disponible")) {
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println("Prestamo no valido");
            e.printStackTrace();
        }
        return false;
    }
    public Prestamo obtenerDTO(Prestamo prestamo){
        return new Prestamo(prestamo.getId(),prestamo.getUsuario(),prestamo.getEjemplar(),prestamo.getFechaInicio(),prestamo.getFechaDevolucion());
    }
    public Prestamo obtenerEntidad(Prestamo prestamoDto){
        return repo.findById(prestamoDto.getId()).get();
    }

    public List<Prestamo> obtenerPrestamoes(){
        List<Prestamo> lista = new ArrayList<>();
        repo.findAll().forEach(e->{
            lista.add(obtenerDTO(e));
        });
        return lista;
    }
    public boolean insertarPrestamo(Prestamo Prestamo){
        try {
            mongo.save(obtenerEntidad(Prestamo));
            repo.save(obtenerEntidad(Prestamo));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean eliminarPrestamo(int id){
        try {
            mongo.deleteById(id);
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean actualizarPrestamo(Prestamo Prestamo){
        try {
            mongo.save(obtenerEntidad(Prestamo));
            repo.save(obtenerEntidad(Prestamo));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
