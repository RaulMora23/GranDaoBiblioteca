package org.example.grandaobiblioteca.controlador;

import org.example.grandaobiblioteca.entidad.Usuario;
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
    private UsuarioServicio servicioUsuario;
    //JSON

    @GetMapping("/JSON")
    public ResponseEntity<List<Usuario>> findALL(){
        return servicioUsuario.findALL();
    }

    @GetMapping("/JSON/{id}")
    public ResponseEntity<Usuario> findUsuario(@PathVariable int id){
        return servicioUsuario.findUsuario(id);
    }

    @PostMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario){
        return servicioUsuario.addUsuario(usuario) == true ? ResponseEntity.ok(usuario) : null;
    }

    @PutMapping(value = "/JSON", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario Usuario) {
        return servicioUsuario.updateUsuario(Usuario) == true ? ResponseEntity.ok(Usuario) : null;
    }

    @DeleteMapping("/JSON/{isbn}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable int id){
        return servicioUsuario.deleteUsuario(id) == true ? ResponseEntity.ok().build() : null;
    }
}
