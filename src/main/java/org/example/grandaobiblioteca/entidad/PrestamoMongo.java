
package org.example.grandaobiblioteca.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "prestamo")
public class PrestamoMongo {

    @Id
    private String id;

    @DBRef
    private UsuarioMongo usuario;

    @DBRef
    private EjemplarMongo ejemplar;

    private LocalDate fechaInicio;

    private LocalDate fechaDevolucion;

    public PrestamoMongo(){}

    public PrestamoMongo(Prestamo prestamo){
        this.id=prestamo.getId().toString();
        this.usuario = new UsuarioMongo(prestamo.getUsuario());
        try{
            this.ejemplar = new EjemplarMongo(prestamo.getEjemplar());
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        this.fechaInicio = prestamo.getFechaInicio();
        this.fechaDevolucion = prestamo.getFechaDevolucion();
    }
}
