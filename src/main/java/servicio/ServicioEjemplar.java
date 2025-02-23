package servicio;

import dto.EjemplarDto;
import entidad.Ejemplar;
import entidad.EjemplarMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorio.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioEjemplar {

    @Autowired
    EjemplarRepository repo;
    @Autowired
    EjemplarMongoRepo mongo;
    @Autowired
    LibroRepository libroRepo;
    @Autowired
    LibroMongoRepo libroMongoRepo;
    @Autowired
    PrestamoRepository prestamoRepo;
    @Autowired
    PrestamoMongoRepo prestamoMongoRepo;

    public boolean validarEjemplar(EjemplarDto ejemplarDto) {
        if (ejemplarDto.getEstado() != "Disponible" && ejemplarDto.getEstado() != "Dañado" && ejemplarDto.getEstado() != "Prestado") {
            return false;
        }
        String isbn = ejemplarDto.getIsbn();
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

    //Conversiones
    public EjemplarDto obtenerDTO(Ejemplar ejemplar) {
        return new EjemplarDto(ejemplar.getId(), ejemplar.getIsbn().getIsbn(), ejemplar.getEstado());
    }

    public Ejemplar obtenerEntidad(EjemplarDto ejemplarDto) {
        try {
            if (ejemplarDto.getId() == null) {
                return new Ejemplar(libroRepo.getByIsbn(ejemplarDto.getIsbn()), ejemplarDto.getEstado(), prestamoRepo.getByEjemplar_Id(ejemplarDto.getId()));
            }
            return new Ejemplar(ejemplarDto.getId(), libroRepo.getByIsbn(ejemplarDto.getIsbn()), ejemplarDto.getEstado(), prestamoRepo.getByEjemplar_Id(ejemplarDto.getId()));
        } catch (Exception e) {
            System.out.println("No se pudo instanciar Ejemplar");
        }
        return null;
    }

    public EjemplarMongo obtenerMongo(EjemplarDto ejemplarDto) {
        try {
            if (ejemplarDto.getId() == null) {
                return new EjemplarMongo(libroRepo.getByIsbn(ejemplarDto.getIsbn()), ejemplarDto.getEstado(), prestamoRepo.getByEjemplar_Id(ejemplarDto.getId()));
            }
            return new EjemplarMongo(ejemplarDto.getId(), libroMongoRepo.getByIsbn(ejemplarDto.getIsbn()), ejemplarDto.getEstado(), prestamoMongoRepo.getByEjemplar_Id(ejemplarDto.getId()));
        } catch (Exception e) {
            System.out.println("No se pudo instanciar EjemplarMongo");
        }
        return null;
    }

    public List<EjemplarDto> obtenerEjemplares() {
        List<EjemplarDto> lista = new ArrayList<>();
        repo.findAll().forEach(e -> {
            lista.add(obtenerDTO(e));
        });
        return lista;
    }

    public boolean insertarEjemplar(EjemplarDto ejemplarDto) {
        try {
            repo.save(obtenerEntidad(ejemplarDto));
            mongo.save(obtenerMongo(ejemplarDto));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean eliminarEjemplar(int id) {
        try {
            mongo.deleteById(id);
            repo.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean actualizarEjemplar(EjemplarDto ejemplarDto) {
        //Aqui podriamos confirmar que el id exista o dejar que se cree en el id elegido
        try {
            mongo.save(obtenerMongo(ejemplarDto));
            repo.save(obtenerEntidad(ejemplarDto));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public Boolean insertarEjemplarTexto(String texto){
        String[] lineas = texto.split(",");
        return this.insertarEjemplar(new EjemplarDto(Integer.parseInt(lineas[0]), lineas[1], lineas[2]));
    }
    public  Boolean modificarEjemplarTexto(String texto){
        String[] lineas = texto.split(",");
        return this.modificarEjemplarTexto(texto);
    }
    public Boolean AddEjemplarTexto(String texto){
        String[] lineas = texto.split(",");
        return this.insertarEjemplar(new EjemplarDto(Integer.parseInt(lineas[0]), lineas[1], lineas[2]));
    }
    public Boolean eliminarEjemplarTexto(String texto){
        String[] lineas = texto.split(",");
        return this.eliminarEjemplar(Integer.parseInt(lineas[0]));
    }
    public String obtenerEjemplarTexto(){
        List<EjemplarDto> lista = obtenerEjemplares();
        StringBuilder texto = new StringBuilder();
        for (EjemplarDto ejemplar : lista){
            texto.append(ejemplar.toString());
            return texto.toString();
        }
        return texto.toString();
    }
}
