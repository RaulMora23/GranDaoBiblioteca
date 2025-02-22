package servicio;

import dto.EjemplarDto;
import entidad.Ejemplar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorio.EjemplarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioEjemplar {

    @Autowired
    EjemplarRepository repo;

    public boolean validarEjemplar(EjemplarDto ejemplarDto){
        String isbn = ejemplarDto.getIsbn();
        isbn = isbn.replaceAll("-","");
        int i = 1;
        int valor = 0;
        try {
            for (char caracter : isbn.toCharArray()) {
                if (i == 14 && valor % 10 == 0) {
                    return true;
                } else if (i % 2 == 1) {
                    valor = valor + Integer.parseInt(String.valueOf(caracter));
                } else {
                    valor = valor + Integer.parseInt(String.valueOf(caracter)) * 3;
                }
            }
        }catch(Exception e){
            System.out.println("Isbn no valido");
            e.printStackTrace();
        }
        return false;
    }
    public EjemplarDto obtenerDTO(Ejemplar ejemplar){
        return new EjemplarDto(ejemplar.getId(),ejemplar.getIsbn().getIsbn(),ejemplar.getEstado());
    }
    public Ejemplar obtenerEntidad(EjemplarDto ejemplarDto){
        return repo.findById(ejemplarDto.getId()).get();
    }

    public List<EjemplarDto> obtenerEjemplares(){
        List<EjemplarDto> lista = new ArrayList<>();
        repo.findAll().forEach(e->{
            lista.add(obtenerDTO(e));
        });
        return lista;
    }
    public boolean insertarEjemplar(EjemplarDto ejemplarDto){
        try {
            repo.save(obtenerEntidad(ejemplarDto));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean eliminarEjemplar(int id){
        try {
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean actualizarEjemplar(EjemplarDto ejemplarDto){
        try {
            repo.save(obtenerEntidad(ejemplarDto));
        }catch (Exception e){}
    }
}
