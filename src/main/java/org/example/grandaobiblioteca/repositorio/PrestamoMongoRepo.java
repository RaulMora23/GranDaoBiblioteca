package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.Prestamo;
import org.example.grandaobiblioteca.entidad.PrestamoMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PrestamoMongoRepo extends MongoRepository<PrestamoMongo,Integer> {
    Set<Prestamo> getByEjemplar_Id(Integer ejemplarId);
}
