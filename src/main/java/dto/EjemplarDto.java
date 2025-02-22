package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entidad.Ejemplar;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * DTO for {@link Ejemplar}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class EjemplarDto implements Serializable {
    Integer id;
    String Isbn;
    String estado;


    public Integer getId() {
        return id;
    }

    public String getIsbn() {
        return Isbn;
    }

    public String getEstado() {
        return estado;
    }
}

