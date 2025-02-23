package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.EjemplarMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarMongoRepo extends MongoRepository<EjemplarMongo, Integer> {
}
