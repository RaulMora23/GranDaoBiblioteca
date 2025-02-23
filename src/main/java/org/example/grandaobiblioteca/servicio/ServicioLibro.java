package org.example.grandaobiblioteca.servicio;

import org.example.grandaobiblioteca.dto.LibroDto;
import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.example.grandaobiblioteca.entidad.EjemplarMongo;
import org.example.grandaobiblioteca.entidad.Libro;
import org.example.grandaobiblioteca.entidad.LibroMongo;
import org.example.grandaobiblioteca.repositorio.EjemplarMongoRepo;
import org.example.grandaobiblioteca.repositorio.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.grandaobiblioteca.repositorio.LibroMongoRepo;
import org.example.grandaobiblioteca.repositorio.LibroRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServicioLibro {
    
    @Autowired
    LibroRepository repo;
    @Autowired
    LibroMongoRepo mongo;
    @Autowired
    EjemplarRepository ejemplarRepo;
    @Autowired
    EjemplarMongoRepo ejemplarMongoRepo;

    public boolean validarLibro(LibroDto libroDto){
        String isbn = libroDto.getIsbn();
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
    public LibroDto obtenerDTO(Libro libro){
        return new LibroDto(libro.getIsbn(),libro.getTitulo(),libro.getAutor());
    }
    public Libro obtenerEntidad(LibroDto libroDto){
        return new Libro(libroDto.getIsbn(),libroDto.getTitulo(),libroDto.getAutor(),ejemplarRepo.getAllByIsbn_Isbn(libroDto.getIsbn()));
    }
    public LibroMongo obtenerMongo(LibroDto libroDto){
        return new LibroMongo(libroDto.getIsbn(),libroDto.getTitulo(),libroDto.getAutor(),new HashSet<>(ejemplarMongoRepo.getAllByIsbn_Isbn(libroDto.getIsbn())));
    }

    public List<LibroDto> obtenerLibros(){
        List<LibroDto> lista = new ArrayList<>();
        repo.findAll().forEach(e->{
            lista.add(obtenerDTO(e));
        });
        return lista;
    }
    public boolean insertarLibro(LibroDto libroDto){
        try {
//            mongo.save(obtenerEntidad(libroDto));
//            repo.save(obtenerEntidad(libroDto));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean eliminarLibro(String isbn){
        try {
            mongo.deleteById(isbn);
            repo.deleteById(isbn);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean actualizarLibro(LibroDto libroDto){
        try {
//            mongo.save(obtenerEntidad(libroDto));
//            repo.save(obtenerEntidad(libroDto));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
