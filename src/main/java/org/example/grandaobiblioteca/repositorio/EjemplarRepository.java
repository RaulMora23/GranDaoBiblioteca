package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {
    Set<Ejemplar> getEjemplarsByIsbn_Isbn(String isbnIsbn);

    Set<Ejemplar> getAllByIsbn_Isbn(String isbnIsbn);
}