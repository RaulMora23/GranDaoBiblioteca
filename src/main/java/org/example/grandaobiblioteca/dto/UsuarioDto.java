package org.example.grandaobiblioteca.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.grandaobiblioteca.entidad.Usuario;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Usuario}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDto implements Serializable {
    Integer id;
    String dni;
    String nombre;
    String email;
    String password;
    String tipo;
    LocalDate penalizacionHasta;
}