package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entidad.Ejemplar;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Ejemplar}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class EjemplarVista implements Serializable {
    Integer id;
    String isbnIsbn;
    String isbnTitulo;
    String estado;
}