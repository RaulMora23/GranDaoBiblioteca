package repositorio;

import entidad.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioMongo extends MongoRepository<Usuario,Integer> {
}
