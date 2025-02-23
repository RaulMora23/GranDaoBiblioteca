package org.example.grandaobiblioteca.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.grandaobiblioteca.entidad.Ejemplar;

import java.io.Serializable;

/**
 * DTO for {@link Ejemplar}
 */
@XmlRootElement(name = "ejemplar")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EjemplarDto implements Serializable {
    @XmlElement
    Integer id;
    @XmlElement
    String Isbn;
    @XmlElement
    String estado;

    public EjemplarDto(int i, String isbn, String estado) {
        this.id = i;
        Isbn = isbn;
        estado = estado;
    }

    public EjemplarDto(String isbn, String estado) {
        Isbn = isbn;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public String getIsbn() {
        return Isbn;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return id + "," +Isbn + "," + estado + '\n';
    }
}

