package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioMongoRepo extends MongoRepository<Usuario,Integer> {
}
