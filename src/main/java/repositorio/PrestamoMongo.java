package repositorio;

import entidad.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoMongo extends MongoRepository<Prestamo,Integer> {
}
