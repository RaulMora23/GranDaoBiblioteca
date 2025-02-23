package org.example.grandaobiblioteca.controlador;

import org.example.grandaobiblioteca.entidad.Prestamo;
import org.example.grandaobiblioteca.servicio.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Prestamo")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio servicioPrestamo;

    @GetMapping("/JSON")
    public List<Prestamo> findAllPrestamo(){
        return servicioPrestamo.obtenerPrestamoes();
    }

    @PostMapping(value = "/JSON", consumes = "application/json", produces = "application/json")
    public boolean addPrestamo(@RequestBody Prestamo prestamo){
        return servicioPrestamo.insertarPrestamo(prestamo);
    }

    @PutMapping(value = "/JSON", consumes = "application/json", produces = "application/json")
    public boolean updatePrestamo(@RequestBody Prestamo prestamo) {
        return servicioPrestamo.actualizarPrestamo(prestamo);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public boolean deletePrestamo(@PathVariable int id) {
        return servicioPrestamo.eliminarPrestamo(id);
    }










}
