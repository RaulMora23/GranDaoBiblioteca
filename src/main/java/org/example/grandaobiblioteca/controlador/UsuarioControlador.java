package org.example.grandaobiblioteca.controlador;

import org.example.grandaobiblioteca.entidad.Usuario;
import org.example.grandaobiblioteca.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio servicio;
    //JSON

    @GetMapping("/JSON")
    public ResponseEntity<List<Usuario>> findALLUsuario(){
        return servicio.findALLUsuario();
    }

    @GetMapping("/JSON/{id}")
    public ResponseEntity<Usuario> findUsuario(@PathVariable int id){
        return servicio.findUsuario(id);
    }

    @PostMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario){
        return servicio.addUsuario(usuario) == true ? ResponseEntity.ok(usuario) : null;
    }

    @PutMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario Usuario) {
        return servicio.updateUsuario(Usuario) == true ? ResponseEntity.ok(Usuario) : null;
    }

    @DeleteMapping("/JSON/{isbn}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable int id){
        return servicio.deleteUsuario(id) == true ? ResponseEntity.ok().build() : null;
    }


    @GetMapping(value = "/XML/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Usuario> findUsuarioXML(@PathVariable int id){
        return servicio.findUsuario(id);
    }
    @GetMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Usuario>> findUsuarioALL(){
        return servicio.findALLUsuario();
    }
    @PostMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Usuario> addUsuarioXML(@RequestBody Usuario Usuario) {
        return servicio.addUsuario(Usuario) == true ? ResponseEntity.ok(Usuario) : null;
    }
    @PutMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Usuario> updateUsuarioXML(@RequestBody Usuario Usuario){
        return servicio.updateUsuario(Usuario) == true ? ResponseEntity.ok(Usuario) : null;
    }
    @DeleteMapping(value = "/XML/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Usuario> deleteUsuarioXML(@PathVariable int id) {
        return servicio.deleteUsuario(id) == true ? ResponseEntity.ok().build() : null;
    }

    // Texto

    @GetMapping("/TEXT")
    public String findALLTextUsuario(){
        return servicio.findALLText();
    }
    @GetMapping(value = "/TEXT/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String findUsuarioText(@PathVariable int id){
        return servicio.findUsuario(id).getBody().toString();
    }

    @PostMapping(value = "/TEXT", produces = MediaType.TEXT_PLAIN_VALUE)
    public String addUsuarioText(@RequestBody String texto){
        return servicio.addUsuarioText(texto);
    }
    @PutMapping(value = "/TEXT/{isbn}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String updateUsuarioText(@RequestBody String texto, @PathVariable String isbn){
        return servicio.updateUsuarioText(texto);
    }

}
