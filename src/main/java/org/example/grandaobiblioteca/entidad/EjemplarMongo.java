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
    private LibroMongo isbn;
    @ColumnDefault("'Disponible'")
    private String estado;
    @DBRef
    private Set<PrestamoMongo> prestamos = new LinkedHashSet<>();

    public EjemplarMongo(Integer id, LibroMongo isbn, String estado, Set<PrestamoMongo> prestamos) {
        this.id = id;
        this.isbn = isbn;
        this.estado = estado;
        this.prestamos = prestamos;
    }

    public EjemplarMongo(LibroMongo isbn, String estado, Set<PrestamoMongo> prestamos) {
        this.isbn = isbn;
        this.estado = estado;
        this.prestamos = prestamos;
    }
    public EjemplarMongo() {

    }
    public EjemplarMongo(Ejemplar ejemplar) {
        id = ejemplar.getId();
        isbn = new LibroMongo(ejemplar.getIsbn());
        estado = ejemplar.getEstado();
        Set<PrestamoMongo> prestamos = new LinkedHashSet<>();
        for (Prestamo prestamo : ejemplar.getPrestamos()) {
            prestamos.add(new PrestamoMongo(prestamo));
        }
        this.prestamos = prestamos;
    }
}