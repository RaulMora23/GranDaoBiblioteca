package org.example.grandaobiblioteca.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    @OneToMany(mappedBy = "isbn")
    private Set<Ejemplar> ejemplars = new LinkedHashSet<>();

    public Libro(String isbn, String titulo, String autor, Set<Ejemplar> ejemplars) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplars = ejemplars;
    }

    public Libro() {

    }
}