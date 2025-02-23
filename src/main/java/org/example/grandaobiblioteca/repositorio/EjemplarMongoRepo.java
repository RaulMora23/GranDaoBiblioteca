package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.EjemplarMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjemplarMongoRepo extends MongoRepository<EjemplarMongo, Integer> {
    List<EjemplarMongo> getAllByIsbn_Isbn(String isbnIsbn);
}
