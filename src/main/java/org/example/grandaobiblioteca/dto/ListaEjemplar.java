package org.example.grandaobiblioteca.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import java.util.List;

@XmlRootElement(name = "listaEjemplar")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaEjemplar {

    @XmlElement(name = "ejemplar") // Define cada elemento dentro de la lista
    private List<EjemplarDto> ejemplares;

    public ListaEjemplar() {}

    public ListaEjemplar(List<EjemplarDto> ejemplares) {
        this.ejemplares = ejemplares;
    }

    public List<EjemplarDto> getEjemplares() { return ejemplares; }

    public void setEjemplares(List<EjemplarDto> ejemplares) { this.ejemplares = ejemplares; }
}
