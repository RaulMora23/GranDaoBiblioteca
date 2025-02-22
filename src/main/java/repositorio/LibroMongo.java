package repositorio;

import entidad.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroMongo extends MongoRepository<Libro,String> {
}
