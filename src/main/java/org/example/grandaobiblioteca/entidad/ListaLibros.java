package org.example.grandaobiblioteca.entidad;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "libros")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaLibros {
    @XmlElement(name = "libro")
    private List<LibroDTO> libros;
    public List<LibroDTO> getLibros() { return libros; }
    public void setLibros(List<LibroDTO> personas) { this.libros = personas; }
}
