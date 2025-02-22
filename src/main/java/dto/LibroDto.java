package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entidad.Libro;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Libro}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDto implements Serializable {
    String isbn;
    String titulo;
    String autor;
}