package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entidad.Ejemplar;

import java.io.Serializable;

/**
 * DTO for {@link Ejemplar}
 */
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

