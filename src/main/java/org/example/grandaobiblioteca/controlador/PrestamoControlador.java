package org.example.grandaobiblioteca.controlador;

import org.example.grandaobiblioteca.entidad.Prestamo;
import org.example.grandaobiblioteca.servicio.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Prestamo")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio servicioPrestamo;

    @GetMapping("/JSON")
    public ResponseEntity<List<Prestamo>> findAllPrestamo(){return servicioPrestamo.findALL();}

    @PostMapping(value = "/JSON", consumes = "application/json", produces = "application/json")
    public boolean addPrestamo(@RequestBody Prestamo prestamo){
        if(servicioPrestamo.validarPrestamo(prestamo)) {
            return servicioPrestamo.addPrestamo(prestamo);
        }
        return false;
    }

    @PutMapping(value = "/JSON", consumes = "application/json", produces = "application/json")
    public boolean updatePrestamo(@RequestBody Prestamo prestamo) {
        if(servicioPrestamo.validarPrestamo(prestamo)) {
            return servicioPrestamo.updatePrestamo(prestamo);
        }
        return false;
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public boolean deletePrestamo(@PathVariable int id) {
        return servicioPrestamo.deletePrestamo(id);
    }

    @GetMapping(value = "/XML/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Prestamo> findPrestamoXML(@PathVariable int id){
        return servicioPrestamo.findPrestamo(id);
    }
    @GetMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Prestamo>> findPrestamoALL(){
        return servicioPrestamo.findALL();
    }
    @PostMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Prestamo> addPrestamoXML(@RequestBody Prestamo Prestamo) {
        if(servicioPrestamo.validarPrestamo(Prestamo)){
            return servicioPrestamo.addPrestamo(Prestamo) == true ? ResponseEntity.ok(Prestamo) : null;
        }
        return null;
    }
    @PutMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Prestamo> updatePrestamoXML(@RequestBody Prestamo Prestamo){
        if(servicioPrestamo.validarPrestamo(Prestamo)){
        return servicioPrestamo.updatePrestamo(Prestamo) == true ? ResponseEntity.ok(Prestamo) : null;
        }
        return null;
    }

    // Texto

    @GetMapping("/TEXT")
    public String findALLText(){
        return servicioPrestamo.findALLText();
    }
    @GetMapping(value = "/TEXT/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findPrestamoText(@PathVariable int id){
        return servicioPrestamo.findPrestamo(id).getBody().toString();
    }

    @PostMapping(value = "/TEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public String addPrestamoText(@RequestBody String texto){
        return servicioPrestamo.addPrestamoText(texto);
    }
    @PutMapping(value = "/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updatePrestamoText(@RequestBody String texto, @PathVariable String isbn){
        return servicioPrestamo.updatePrestamoText(texto);
    }










}
