package repositorio;

import entidad.Ejemplar;
import entidad.EjemplarMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarMongoRepo extends MongoRepository<EjemplarMongo, Integer> {
}
