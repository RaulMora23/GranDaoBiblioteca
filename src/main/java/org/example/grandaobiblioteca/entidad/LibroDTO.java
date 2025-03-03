package org.example.grandaobiblioteca.entidad;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibroDTO {
    @XmlElement(name = "isbn")  // Mapea el campo 'isbn' al elemento XML 'isbn'
    private String isbn;

    @XmlElement(name = "titulo")  // Mapea el campo 'titulo' al elemento XML 'titulo'
    private String titulo;

    @XmlElement(name = "autor")  // Mapea el campo 'autor' al elemento XML 'autor'
    private String autor;

    public LibroDTO() {}

    public LibroDTO(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public LibroDTO(Libro libro) {
        this.isbn = libro.getIsbn();
        this.titulo = libro.getTitulo();
        this.autor = libro.getAutor();
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
}
