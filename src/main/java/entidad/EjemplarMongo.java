package entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

    private String estado;

    private Set<Prestamo> prestamos = new LinkedHashSet<>();

}