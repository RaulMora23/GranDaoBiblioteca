package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {
}