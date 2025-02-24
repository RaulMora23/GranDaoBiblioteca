package org.example.grandaobiblioteca.controlador;

import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.example.grandaobiblioteca.servicio.EjemplarServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ejemplar")
public class EjemplarControlador {

    @Autowired
    private EjemplarServicio servicio;
    //JSON

    @GetMapping("/JSON")
    public ResponseEntity<List<Ejemplar>> findALL(){
        return servicio.findALL();
    }

    @GetMapping("/JSON/{id}")
    public ResponseEntity<Ejemplar> findEjemplar(@PathVariable int id){
        return servicio.findEjemplar(id);
    }

    @PostMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ejemplar> addEjemplar(@RequestBody Ejemplar ejemplar){
        if(servicio.validarEjemplar(ejemplar)) {
            return servicio.addEjemplar(ejemplar) == true ? ResponseEntity.ok(ejemplar) : null;
        }
        return null;
    }

    @PutMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar) {
        if(servicio.validarEjemplar(ejemplar)) {
            return servicio.updateEjemplar(ejemplar) == true ? ResponseEntity.ok(ejemplar) : null;
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Ejemplar> deleteEjemplar(@PathVariable int id){
        return servicio.deleteEjemplar(id) == true ? ResponseEntity.ok().build() : null;
    }

    //XML

    @GetMapping(value = "/XML/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Ejemplar> findEjemplarXML(@PathVariable int id){
        return servicio.findEjemplar(id);
    }
    @GetMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Ejemplar>> findEjemplarALL(){
        return servicio.findALL();
    }
    @PostMapping(value = "/XML", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Ejemplar> addEjemplarXML(@RequestBody Ejemplar ejemplar) {
        if(servicio.validarEjemplar(ejemplar)) {
            return servicio.addEjemplar(ejemplar) == true ? ResponseEntity.ok(ejemplar) : null;
        }
        return null;
    }
    @PutMapping(value = "/XML",consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Ejemplar> updateEjemplarXML(@RequestBody Ejemplar Ejemplar){
        if(servicio.validarEjemplar(Ejemplar)) {
            return servicio.updateEjemplar(Ejemplar) == true ? ResponseEntity.ok(Ejemplar) : null;
        }
        return null;
    }

    // Texto

    @GetMapping("/TEXT")
    public String findALLText(){
        return servicio.findALLTextEjemplar();
    }
    @PostMapping(value = "/TEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public String addEjemplarText(@RequestBody String texto){
        return servicio.addEjemplarText(texto);
    }
    @PutMapping(value = "/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateEjemplarText(@RequestBody String texto){
        return servicio.updateEjemplarText(texto);
    }

}
