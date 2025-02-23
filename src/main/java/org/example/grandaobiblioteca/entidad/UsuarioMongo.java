package org.example.grandaobiblioteca.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Document(collection = "usuario")
public class UsuarioMongo {

    @Id
    private Integer id;

    private String dni;

    private String nombre;

    private String email;

    private String password;

    private String tipo;

    private LocalDate penalizacionHasta;

    @DBRef
    private Set<PrestamoMongo> prestamos = new LinkedHashSet<>();

    public UsuarioMongo(){};

    public UsuarioMongo(Usuario usuario){
        this.id= usuario.getId();
        this.dni = usuario.getDni();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.tipo = usuario.getTipo();
        this.penalizacionHasta = usuario.getPenalizacionHasta();
        Set<PrestamoMongo> prestamos = new LinkedHashSet<>();
        for (Prestamo prestamo : usuario.getPrestamos()) {
            prestamos.add(new PrestamoMongo(prestamo));
        }
        this.prestamos = prestamos;
    }
}

