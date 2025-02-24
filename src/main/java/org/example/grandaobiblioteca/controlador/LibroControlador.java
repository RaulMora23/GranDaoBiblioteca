package org.example.grandaobiblioteca.controlador;

import org.example.grandaobiblioteca.entidad.Libro;
import org.example.grandaobiblioteca.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroControlador {

    @Autowired
    private LibroServicio servicio;
    //JSON

    @GetMapping("/JSON")
    public ResponseEntity<List<Libro>> findALLLibros(){
        return servicio.findALLLibro();
    }

    @GetMapping("/JSON/{isbn}")
    public ResponseEntity<Libro> findLibro(@PathVariable String isbn){
        return servicio.findLibro(isbn);
    }

    @PostMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        if(servicio.validarLibro(libro)) {
            return servicio.addLibro(libro) == true ? ResponseEntity.ok(libro) : null;
        }
        return null;
    }

    @PutMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro) {
        if(servicio.validarLibro(libro)) {
            return servicio.updateLibro(libro) == true ? ResponseEntity.ok(libro) : null;
        }
        return null;
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Libro> deleteLibro(@PathVariable String isbn){
        return servicio.deleteLibro(isbn) == true ? ResponseEntity.ok().build() : null;
    }

    //XML

    @GetMapping(value = "/XML/{isbn}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> findLibroXML(@PathVariable String isbn){
        return servicio.findLibro(isbn);
    }
    @GetMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Libro>> findLibroALL(){
        return servicio.findALLLibro();
    }
    @PostMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> addLibroXML(@RequestBody Libro libro) {
        if(servicio.validarLibro(libro)) {
            return servicio.addLibro(libro) == true ? ResponseEntity.ok(libro) : null;
        }
        return null;
    }


    @PutMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> updateLibroXML(@RequestBody Libro libro){
        if(servicio.validarLibro(libro)) {
            return servicio.updateLibro(libro) == true ? ResponseEntity.ok(libro) : null;
        }
        return null;
    }

    // Texto

    @GetMapping("/TEXT")
    public String findALLTextLibro(){
        return servicio.findALLTextLibro();
    }
    @GetMapping(value = "/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findLibroText(@PathVariable String isbn){
        return servicio.findLibro(isbn).getBody().toString();
    }

    @PostMapping(value = "/TEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public String addLibroText(@RequestBody String texto){
        return servicio.addLibroText(texto);
    }
    @PutMapping(value = "/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateLibroText(@RequestBody String texto, @PathVariable String isbn){
        return servicio.updateLibroText(texto);
    }

}
