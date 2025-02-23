package org.example.grandaobiblioteca.repositorio;

import jakarta.validation.constraints.Size;
import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.example.grandaobiblioteca.entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String> {
    boolean findByIsbn(String isbn);

    Libro getByIsbn(String isbn);

    Ejemplar getLibroByIsbn(@Size(max = 20) String isbn);
}