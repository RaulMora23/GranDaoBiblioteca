package org.example.grandaobiblioteca.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ejemplar_id", nullable = false)
    private Ejemplar ejemplar;

    @Column(name = "fecha_Inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_Devolucion")
    private LocalDate fechaDevolucion;

    public Prestamo(Integer id, Usuario usuario, Ejemplar ejemplar, LocalDate fechaInicio, LocalDate fechaDevolucion) {
        this.id = id;
        this.usuario = usuario;
        this.ejemplar = ejemplar;
        this.fechaInicio = fechaInicio;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Prestamo(Usuario usuario, Ejemplar ejemplar, LocalDate fechaInicio, LocalDate fechaDevolucion) {
        this.usuario = usuario;
        this.ejemplar = ejemplar;
        this.fechaInicio = fechaInicio;
        this.fechaDevolucion = fechaDevolucion;
    }
}