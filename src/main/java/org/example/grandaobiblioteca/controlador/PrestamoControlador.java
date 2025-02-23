package org.example.grandaobiblioteca.controlador;

import org.example.grandaobiblioteca.entidad.Prestamo;
import org.example.grandaobiblioteca.servicio.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Prestamo")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio servicioPrestamo;

    @GetMapping("/JSON")
    public ResponseEntity<List<Prestamo>> findAllPrestamo(){
        return servicioPrestamo.findALL();
    }

    @PostMapping(value = "/JSON", consumes = "application/json", produces = "application/json")
    public boolean addPrestamo(@RequestBody Prestamo prestamo){
        return servicioPrestamo.addPrestamo(prestamo);
    }

    @PutMapping(value = "/JSON", consumes = "application/json", produces = "application/json")
    public boolean updatePrestamo(@RequestBody Prestamo prestamo) {
        return servicioPrestamo.updatePrestamo(prestamo);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public boolean deletePrestamo(@PathVariable int id) {
        return servicioPrestamo.deletePrestamo(id);
    }










}
