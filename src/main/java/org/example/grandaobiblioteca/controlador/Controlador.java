package org.example.grandaobiblioteca.controlador;

import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.example.grandaobiblioteca.entidad.Libro;
import org.example.grandaobiblioteca.entidad.Prestamo;
import org.example.grandaobiblioteca.entidad.Usuario;
import org.example.grandaobiblioteca.servicio.EjemplarServicio;
import org.example.grandaobiblioteca.servicio.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class Controlador {

    @Autowired
    private Servicio servicio;

    //EJEMPLAR

    //JSON
    @GetMapping("/ejemplar/JSON")
    public ResponseEntity<List<Ejemplar>> findALL() {
        return servicio.findALLEjemplar();
    }

    @GetMapping("/ejemplar/JSON/{id}")
    public ResponseEntity<Ejemplar> findEjemplar(@PathVariable int id) {
        return servicio.findEjemplar(id);
    }

    @PostMapping(value = "/ejemplar/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ejemplar> addEjemplar(@RequestBody Ejemplar ejemplar) {
        if (servicio.validarEjemplar(ejemplar)) {
            return servicio.addEjemplar(ejemplar) == true ? ResponseEntity.ok(ejemplar) : null;
        }
        return null;
    }

    @PutMapping(value = "/ejemplar/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar) {
        if (servicio.validarEjemplar(ejemplar)) {
            return servicio.updateEjemplar(ejemplar) == true ? ResponseEntity.ok(ejemplar) : null;
        }
        return null;
    }

    @DeleteMapping("/ejemplar/{id}")
    public ResponseEntity<Ejemplar> deleteEjemplar(@PathVariable int id) {
        return servicio.deleteEjemplar(id) == true ? ResponseEntity.ok().build() : null;
    }

    //XML

    @GetMapping(value = "/ejemplar/XML/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Ejemplar> findEjemplarXML(@PathVariable int id) {
        return servicio.findEjemplar(id);
    }

    @GetMapping(value = "/ejemplar/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Ejemplar>> findEjemplarALL() {
        return servicio.findALLEjemplar();
    }

    @PostMapping(value = "/ejemplar/XML", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Ejemplar> addEjemplarXML(@RequestBody Ejemplar ejemplar) {
        if (servicio.validarEjemplar(ejemplar)) {
            return servicio.addEjemplar(ejemplar) == true ? ResponseEntity.ok(ejemplar) : null;
        }
        return null;
    }

    @PutMapping(value = "/ejemplar/XML", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Ejemplar> updateEjemplarXML(@RequestBody Ejemplar Ejemplar) {
        if (servicio.validarEjemplar(Ejemplar)) {
            return servicio.updateEjemplar(Ejemplar) == true ? ResponseEntity.ok(Ejemplar) : null;
        }
        return null;
    }

    // Texto

    @GetMapping("/ejemplar/TEXT")
    public String findALLText() {
        return servicio.findALLTextEjemplar();
    }

    @PostMapping(value = "/ejemplar/TEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public String addEjemplarText(@RequestBody String texto) {
        return servicio.addEjemplarText(texto);
    }

    @PutMapping(value = "/ejemplar/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateEjemplarText(@RequestBody String texto) {
        return servicio.updateEjemplarText(texto);
    }

    //LIBRO

    @GetMapping("/libro/JSON")
    public ResponseEntity<List<Libro>> findALLLibros(){
        return servicio.findALLLibro();
    }

    @GetMapping("/libro/JSON/{isbn}")
    public ResponseEntity<Libro> findLibro(@PathVariable String isbn){
        return servicio.findLibro(isbn);
    }

    @PostMapping(value = "/libro/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        if(servicio.validarLibro(libro)) {
            return servicio.addLibro(libro) == true ? ResponseEntity.ok(libro) : null;
        }
        return null;
    }

    @PutMapping(value = "/libro/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro) {
        if(servicio.validarLibro(libro)) {
            return servicio.updateLibro(libro) == true ? ResponseEntity.ok(libro) : null;
        }
        return null;
    }

    @DeleteMapping("/libro/{isbn}")
    public ResponseEntity<Libro> deleteLibro(@PathVariable String isbn){
        return servicio.deleteLibro(isbn) == true ? ResponseEntity.ok().build() : null;
    }

    //XML

    @GetMapping(value = "/libro/XML/{isbn}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> findLibroXML(@PathVariable String isbn){
        return servicio.findLibro(isbn);
    }
    @GetMapping(value = "/libro/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Libro>> findLibroALL(){
        return servicio.findALLLibro();
    }
    @PostMapping(value = "/libro/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> addLibroXML(@RequestBody Libro libro) {
        if(servicio.validarLibro(libro)) {
            return servicio.addLibro(libro) == true ? ResponseEntity.ok(libro) : null;
        }
        return null;
    }


    @PutMapping(value = "/libro/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Libro> updateLibroXML(@RequestBody Libro libro){
        if(servicio.validarLibro(libro)) {
            return servicio.updateLibro(libro) == true ? ResponseEntity.ok(libro) : null;
        }
        return null;
    }

    // Texto

    @GetMapping("/libro/TEXT")
    public String findALLTextLibro(){
        return servicio.findALLTextLibro();
    }
    @GetMapping(value = "/libro/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findLibroText(@PathVariable String isbn){
        return servicio.findLibro(isbn).getBody().toString();
    }

    @PostMapping(value = "/libro/TEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public String addLibroText(@RequestBody String texto){
        return servicio.addLibroText(texto);
    }
    @PutMapping(value = "/libro/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateLibroText(@RequestBody String texto, @PathVariable String isbn){
        return servicio.updateLibroText(texto);
    }

    //PRÉSTAMO

    @GetMapping("/prestamo/JSON")
    public ResponseEntity<List<Prestamo>> findAllPrestamo(){return servicio.findALLPrestamo();}

    @PostMapping(value = "/prestamo/JSON", consumes = "application/json", produces = "application/json")
    public boolean addPrestamo(@RequestBody Prestamo prestamo){
        if(servicio.validarPrestamo(prestamo)) {
            return servicio.addPrestamo(prestamo);
        }
        return false;
    }

    @PutMapping(value = "/prestamo/JSON", consumes = "application/json", produces = "application/json")
    public boolean updatePrestamo(@RequestBody Prestamo prestamo) {
        if(servicio.validarPrestamo(prestamo)) {
            return servicio.updatePrestamo(prestamo);
        }
        return false;
    }

    @DeleteMapping(value = "/prestamo/{id}", produces = "application/json")
    public boolean deletePrestamo(@PathVariable int id) {
        return servicio.deletePrestamo(id);
    }

    @GetMapping(value = "/prestamo/XML/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Prestamo> findPrestamoXML(@PathVariable int id){
        return servicio.findPrestamo(id);
    }
    @GetMapping(value = "/prestamo/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Prestamo>> findPrestamoALL(){
        return servicio.findALLPrestamo();
    }
    @PostMapping(value = "/prestamo/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Prestamo> addPrestamoXML(@RequestBody Prestamo Prestamo) {
        if(servicio.validarPrestamo(Prestamo)){
            return servicio.addPrestamo(Prestamo) == true ? ResponseEntity.ok(Prestamo) : null;
        }
        return null;
    }
    @PutMapping(value = "/prestamo/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Prestamo> updatePrestamoXML(@RequestBody Prestamo Prestamo){
        if(servicio.validarPrestamo(Prestamo)){
            return servicio.updatePrestamo(Prestamo) == true ? ResponseEntity.ok(Prestamo) : null;
        }
        return null;
    }

    // Texto

    @GetMapping("/prestamo/TEXT")
    public String findALLTextPrestamo(){
        return servicio.findALLTextPrestamo();
    }
    @GetMapping(value = "/prestamo/TEXT/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findPrestamoText(@PathVariable int id){
        return servicio.findPrestamo(id).getBody().toString();
    }

    @PostMapping(value = "/prestamo/TEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public String addPrestamoText(@RequestBody String texto){
        return servicio.addPrestamoText(texto);
    }
    @PutMapping(value = "/prestamo/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updatePrestamoText(@RequestBody String texto, @PathVariable String isbn){
        return servicio.updatePrestamoText(texto);
    }

    //USUARIO

    @GetMapping("/usuario/JSON")
    public ResponseEntity<List<Usuario>> findALLUsuario(){
        return servicio.findALLUsuario();
    }

    @GetMapping("/usuario/JSON/{id}")
    public ResponseEntity<Usuario> findUsuario(@PathVariable int id){
        return servicio.findUsuario(id);
    }

    @PostMapping(value = "/usuario/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario){
        return servicio.addUsuario(usuario) == true ? ResponseEntity.ok(usuario) : null;
    }

    @PutMapping(value = "/usuario/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario Usuario) {
        return servicio.updateUsuario(Usuario) == true ? ResponseEntity.ok(Usuario) : null;
    }

    @DeleteMapping("/usuario/JSON/{isbn}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable int id){
        return servicio.deleteUsuario(id) == true ? ResponseEntity.ok().build() : null;
    }


    @GetMapping(value = "/usuario/XML/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Usuario> findUsuarioXML(@PathVariable int id){
        return servicio.findUsuario(id);
    }
    @GetMapping(value = "/usuario/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Usuario>> findUsuarioALL(){
        return servicio.findALLUsuario();
    }
    @PostMapping(value = "/usuario/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Usuario> addUsuarioXML(@RequestBody Usuario Usuario) {
        return servicio.addUsuario(Usuario) == true ? ResponseEntity.ok(Usuario) : null;
    }
    @PutMapping(value = "/usuario/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Usuario> updateUsuarioXML(@RequestBody Usuario Usuario){
        return servicio.updateUsuario(Usuario) == true ? ResponseEntity.ok(Usuario) : null;
    }
    @DeleteMapping(value = "/usuario/XML/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Usuario> deleteUsuarioXML(@PathVariable int id) {
        return servicio.deleteUsuario(id) == true ? ResponseEntity.ok().build() : null;
    }

    // Texto

    @GetMapping("/usuario/TEXT")
    public String findALLTextUsuario(){
        return servicio.findALLText();
    }
    @GetMapping(value = "/usuario/TEXT/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findUsuarioText(@PathVariable int id){
        return servicio.findUsuario(id).getBody().toString();
    }

    @PostMapping(value = "/usuario/TEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public String addUsuarioText(@RequestBody String texto){
        return servicio.addUsuarioText(texto);
    }
    @PutMapping(value = "/usuario/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateUsuarioText(@RequestBody String texto, @PathVariable String isbn){
        return servicio.updateUsuarioText(texto);
    }
}
