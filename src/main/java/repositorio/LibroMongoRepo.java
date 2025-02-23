package repositorio;

import entidad.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroMongoRepo extends MongoRepository<LibroMongo,String> {
    Libro getByIsbn(String isbn);
}
