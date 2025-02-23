package repositorio;

import entidad.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioMongoRepo extends MongoRepository<Usuario,Integer> {
}
