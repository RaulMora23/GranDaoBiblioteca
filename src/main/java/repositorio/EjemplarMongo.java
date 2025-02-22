package repositorio;

import entidad.Ejemplar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarMongo extends MongoRepository<Ejemplar, Integer> {
}
