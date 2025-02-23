package repositorio;

import entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String> {
    boolean findByIsbn(String isbn);

    Libro getByIsbn(String isbn);
}