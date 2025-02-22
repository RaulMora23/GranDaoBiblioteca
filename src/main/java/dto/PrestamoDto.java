package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entidad.Prestamo;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Prestamo}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrestamoDto implements Serializable {
    Integer id;
    Integer usuarioId;
    Integer ejemplarId;
    LocalDate fechaInicio;
    LocalDate fechaDevolucion;
}