package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.Usuario;
import org.example.grandaobiblioteca.entidad.UsuarioMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioMongoRepo extends MongoRepository<UsuarioMongo,Integer> {
}
