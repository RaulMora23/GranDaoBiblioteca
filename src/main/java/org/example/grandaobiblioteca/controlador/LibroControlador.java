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
    private LibroServicio libroServicio;
    //JSON

    @GetMapping("/JSON")
    public ResponseEntity<List<Libro>> findALL(){
        return libroServicio.findALL();
    }

    @GetMapping("/JSON/{isbn}")
    public ResponseEntity<Libro> findLibro(@PathVariable String isbn){
        return libroServicio.findLibro(isbn);
    }

    @PostMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        return libroServicio.addLibro(libro) == true ? ResponseEntity.ok(libro) : null;
    }

    @PutMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro) {
        return libroServicio.updateLibro(libro) == true ? ResponseEntity.ok(libro) : null;
    }

    @DeleteMapping("/JSON/{isbn}")
    public ResponseEntity<Libro> deleteLibro(@PathVariable String isbn){
        return libroServicio.deleteLibro(isbn) == true ? ResponseEntity.ok().build() : null;
    }

    //XML

    @GetMapping(value = "/XML/{isbn}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> findLibroXML(@PathVariable String isbn){
        return libroServicio.findLibro(isbn);
    }
    @GetMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Libro>> findLibroALL(){
        return libroServicio.findALL();
    }
    @PostMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> addLibroXML(@RequestBody Libro libro) {
        return libroServicio.addLibro(libro) == true ? ResponseEntity.ok(libro) : null;
    }
    @PutMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> updateLibroXML(@RequestBody Libro libro){
        return libroServicio.updateLibro(libro) == true ? ResponseEntity.ok(libro) : null;
    }
    @DeleteMapping(value = "/XML/{isbn}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> deleteLibroXML(@PathVariable String isbn) {
        return libroServicio.deleteLibro(isbn) == true ? ResponseEntity.ok().build() : null;
    }

    // Texto

    @GetMapping("/TEXT")
    public String findALLText(){
        return libroServicio.findALLText();
    }
    @GetMapping(value = "/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findLibroText(@PathVariable String isbn){
        return libroServicio.findLibro(isbn).getBody().toString();
    }

    @PostMapping(value = "/TEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public String addLibroText(@RequestBody String texto){
        return libroServicio.addLibroText(texto);
    }
    @PutMapping(value = "/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateLibroText(@RequestBody String texto, @PathVariable String isbn){
        return libroServicio.updateLibroText(texto);
    }

}
