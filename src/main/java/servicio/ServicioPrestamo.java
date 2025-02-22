package servicio;

import dto.PrestamoDto;
import entidad.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorio.EjemplarRepository;
import repositorio.PrestamoRepository;
import repositorio.UsuarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPrestamo {

    @Autowired
    PrestamoRepository repo;
    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    EjemplarRepository ejemplarRepo;
    
    public boolean validarPrestamo(PrestamoDto prestamoDto){
        try {
            if (usuarioRepo.findById(prestamoDto.getUsuarioId()).get().getPenalizacionHasta().isAfter(LocalDate.now())) {
                if (ejemplarRepo.findById(prestamoDto.getEjemplarId()).get().getEstado().equals("Disponible")) {
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println("Prestamo no valido");
            e.printStackTrace();
        }
        return false;
    }
    public PrestamoDto obtenerDTO(Prestamo prestamo){
        return new PrestamoDto(prestamo.getId(),prestamo.getUsuario().getId(),prestamo.getEjemplar().getId(),prestamo.getFechaInicio(),prestamo.getFechaDevolucion());
    }
    public Prestamo obtenerEntidad(PrestamoDto prestamoDto){
        return repo.findById(prestamoDto.getId()).get();
    }

    public List<PrestamoDto> obtenerPrestamoes(){
        List<PrestamoDto> lista = new ArrayList<>();
        repo.findAll().forEach(e->{
            lista.add(obtenerDTO(e));
        });
        return lista;
    }
    public boolean insertarPrestamo(PrestamoDto prestamoDto){
        try {
            repo.save(obtenerEntidad(prestamoDto));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean eliminarPrestamo(int id){
        try {
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean actualizarPrestamo(PrestamoDto prestamoDto){
        try {
            repo.save(obtenerEntidad(prestamoDto));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
