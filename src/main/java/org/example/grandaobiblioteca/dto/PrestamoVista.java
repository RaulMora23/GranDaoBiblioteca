package org.example.grandaobiblioteca.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.grandaobiblioteca.entidad.Prestamo;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Prestamo}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrestamoVista implements Serializable {
    Integer id;
    String usuarioNombre;
    String usuarioEmail;
    Integer ejemplarId;
    String ejemplarEstado;
    LocalDate fechaInicio;
    LocalDate fechaDevolucion;
}