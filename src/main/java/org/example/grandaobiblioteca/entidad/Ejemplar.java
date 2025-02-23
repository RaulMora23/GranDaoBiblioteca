package org.example.grandaobiblioteca.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.grandaobiblioteca.repositorio.LibroRepository;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    @JsonBackReference("libro-ejemplar")
    private Libro isbn;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "ejemplar")
    @JsonManagedReference("ejemplar-prestamo")
    private Set<Prestamo> prestamos = new LinkedHashSet<>();

    public Ejemplar(Libro isbn, String estado) {
        this.isbn = isbn;
        this.estado = estado;
    }

    public Ejemplar(Integer id, Libro isbn, String estado) {
        this.id = id;
        this.isbn = isbn;
        this.estado = estado;
    }

    public Ejemplar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Libro getIsbn() {
        return isbn;
    }

    public void setIsbn(Libro isbn) {
        this.isbn = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

<<<<<<< Updated upstream
    @Override
    public String toString() {
        return id + "," + isbn.getIsbn() + "," + estado + "\n";
=======
    public Ejemplar(Integer id, Libro isbn, String estado) {
        this.id = id;
        this.isbn = isbn;
        this.estado = estado;
>>>>>>> Stashed changes
    }
}