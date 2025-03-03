package org.example.grandaobiblioteca.entidad;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "List")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaLibros {
    @XmlElement(name = "item")
    private List<LibroDTO> libros;
    public List<LibroDTO> getLibros() { return libros; }
    public void setLibros(List<LibroDTO> libros) { this.libros = libros;}
    public ListaLibros(List<LibroDTO> libros) {
        this.libros = libros;
    }
    public ListaLibros() {}
}
