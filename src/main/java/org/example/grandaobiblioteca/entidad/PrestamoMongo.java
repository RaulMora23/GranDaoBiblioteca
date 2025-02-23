
package org.example.grandaobiblioteca.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "prestamo")
public class PrestamoMongo {

    @Id
    private Integer id;

    @DBRef
    private UsuarioMongo usuario;

    @DBRef
    private EjemplarMongo ejemplar;

    private LocalDate fechaInicio;

    private LocalDate fechaDevolucion;

    public PrestamoMongo(){}

    public PrestamoMongo(Prestamo prestamo){
        this.id=prestamo.getId();
        this.usuario = new UsuarioMongo(prestamo.getUsuario());
        this.ejemplar = new EjemplarMongo(prestamo.getEjemplar());
        this.fechaInicio = prestamo.getFechaInicio();
        this.fechaDevolucion = prestamo.getFechaDevolucion();
    }
}
