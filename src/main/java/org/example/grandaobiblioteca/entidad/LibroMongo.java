package org.example.grandaobiblioteca.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;
import java.util.Set;

@Document(collection = "libro")
public class LibroMongo {

    @Id
    private String isbn;

    private String titulo;

    private String autor;

    @DBRef
    private Set<EjemplarMongo> ejemplars = new LinkedHashSet<>();

    public LibroMongo(String isbn, String titulo, String autor, Set<EjemplarMongo> ejemplars) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplars = ejemplars;
    }

    public LibroMongo() {
    }

    public LibroMongo(Libro isbn) {
        this.isbn = isbn.getIsbn();
        this.titulo = isbn.getTitulo();
        this.autor = isbn.getAutor();
        Set<EjemplarMongo> ejemplars = new LinkedHashSet<>();
        for (Ejemplar ejemplar : isbn.getEjemplars()) {
            ejemplars.add(new EjemplarMongo(ejemplar));
        }
        this.ejemplars = ejemplars;
    }
}
