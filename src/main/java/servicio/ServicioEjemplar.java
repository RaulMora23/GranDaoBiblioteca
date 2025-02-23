package servicio;

import dto.EjemplarDto;
import entidad.Ejemplar;
import entidad.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorio.EjemplarMongo;
import repositorio.EjemplarRepository;
import repositorio.LibroMongo;
import repositorio.LibroRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioEjemplar {

    @Autowired
    EjemplarRepository repo;
    @Autowired
    EjemplarMongo mongo;
    @Autowired
    LibroRepository libroRepo;

    public boolean validarEjemplar(EjemplarDto ejemplarDto){
        String isbn = ejemplarDto.getIsbn();
        isbn = isbn.replaceAll("-","");
        int i = 1;
        int valor = 0;
        try {
            for (char caracter : isbn.toCharArray()) {
                if (i == 13) {
                    valor = valor + Integer.parseInt(String.valueOf(caracter));
                    if (valor % 10 == 0){
                        return true;
                    }
                } else if (i % 2 == 1) {
                    valor = valor + Integer.parseInt(String.valueOf(caracter));
                } else {
                    valor = valor + Integer.parseInt(String.valueOf(caracter)) * 3;
                }
                i++;
            }
        }catch(Exception e){
            System.out.println("Isbn no valido");
            e.printStackTrace();
        }
        return false;
    }

    public EjemplarDto obtenerDeString(String ejemplar){
        ejemplar = ejemplar.replaceAll("\\(","");
        ejemplar = ejemplar.replaceAll("\\)","");
        String[] datos = ejemplar.split(",");
        return new EjemplarDto(Integer.parseInt(datos[0]),datos[1],datos[2]);
    }

    public EjemplarDto obtenerDTO(Ejemplar ejemplar){
        return new EjemplarDto(ejemplar.getId(),ejemplar.getIsbn().getIsbn(),ejemplar.getEstado());
    }
    public Ejemplar obtenerEntidad(EjemplarDto ejemplarDto){
        return new Ejemplar(ejemplarDto.getId(),libroRepo.findBy(ejemplarDto.getIsbn()),);
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
            mongo.save(obtenerEntidad(ejemplarDto));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean eliminarEjemplar(int id){
        try {
            mongo.deleteById(id);
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean actualizarEjemplar(EjemplarDto ejemplarDto , int id){
        try {
            ejemplarDto.se
            mongo.save(obtenerEntidad(ejemplarDto));
            repo.save(obtenerEntidad(ejemplarDto));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
