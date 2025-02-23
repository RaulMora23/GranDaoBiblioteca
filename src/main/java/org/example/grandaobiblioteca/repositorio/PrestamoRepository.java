package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    Set<Prestamo> findByEjemplar_Id(Integer ejemplarId);

    Set<Prestamo> getByEjemplar_Id(Integer ejemplarId);

    Set<Prestamo> getAllByEjemplar_Id(Integer ejemplarId);
}