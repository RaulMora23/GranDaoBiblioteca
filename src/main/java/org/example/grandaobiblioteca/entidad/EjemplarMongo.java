package org.example.grandaobiblioteca.entidad;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;
import java.util.Set;

@Document(collection = "ejemplar")
public class EjemplarMongo {

    @Id
    private Integer id;

    @DBRef
    private Libro isbn;
    @ColumnDefault("'Disponible'")
    private String estado;
    @DBRef
    private Set<Prestamo> prestamos = new LinkedHashSet<>();

    public EjemplarMongo(Integer id, Libro isbn, String estado, Set<Prestamo> prestamos) {
        this.id = id;
        this.isbn = isbn;
        this.estado = estado;
        this.prestamos = prestamos;
    }

    public EjemplarMongo(Libro isbn, String estado, Set<Prestamo> prestamos) {
        this.isbn = isbn;
        this.estado = estado;
        this.prestamos = prestamos;
    }
}