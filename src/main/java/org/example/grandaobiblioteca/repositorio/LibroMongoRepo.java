package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.LibroMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroMongoRepo extends MongoRepository<LibroMongo,String> {
}
