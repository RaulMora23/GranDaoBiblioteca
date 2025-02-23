package org.example.grandaobiblioteca.servicio;


import org.example.grandaobiblioteca.entidad.Usuario;
import org.example.grandaobiblioteca.entidad.UsuarioMongo;
import org.example.grandaobiblioteca.repositorio.UsuarioMongoRepo;
import org.example.grandaobiblioteca.repositorio.UsuarioRepository;
import org.example.grandaobiblioteca.repositorio.LibroRepository;
import org.example.grandaobiblioteca.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private UsuarioMongoRepo mongo;

    public boolean addUsuario(Usuario usuario){
        try {
            usuarioRepo.save(usuario);
            mongo.save(new UsuarioMongo(usuario));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteUsuario(Integer id){
        try{
            usuarioRepo.deleteById(id);
            mongo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Boolean updateUsuario(Usuario usuario) {
        try {
            // Buscar el usuario por el ISBN proporcionado
            Optional<Usuario> usuarioExistente = usuarioRepo.findById(usuario.getId());

            if (usuarioExistente.isPresent()) {
                //no hace falta existingE es igual a usuario
                /* Obtener el usuario existente
                Usuario existingUsuario = usuarioExistente.get();

                // No modificar el ISBN
                existingUsuario.setAutor(usuario.getAutor());
                existingUsuario.setTitulo(usuario.getTitulo());
                existingUsuario.setUsuarios(usuario.getUsuarios());*/

                // Guardar los cambios en el repositorio
                usuarioRepo.save(usuario);
                mongo.save(new UsuarioMongo(usuario));
                return true;
            } else {
                // Si no se encuentra el usuario, devolver false
                //System.out.println("Usuario con ISBN " + isbn + " no encontrado.");
                return false;
            }
        } catch (Exception e) {
            // Registrar la excepción para depuración
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }

    public String addUsuarioText(String texto){
        String[] linea = texto.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de la fecha
        LocalDate fecha = LocalDate.parse(linea[6], formatter);
        Usuario usuario = new Usuario(Integer.parseInt(linea[0]),linea[1],linea[2],linea[3],linea[4],linea[5],fecha);
        boolean valor = this.addUsuario(usuario);
        return valor == true ? "Usuario añadido" : "Error al añadir el usuario";
    }
    public String updateUsuarioText(String texto){
        String[] linea = texto.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato de la fecha
        LocalDate fecha = LocalDate.parse(linea[6], formatter);
        Usuario usuario = new Usuario(Integer.parseInt(linea[0]),linea[1],linea[2],linea[3],linea[4],linea[5],fecha);
        boolean valor = this.updateUsuario(usuario);
        return valor == true ? "Usuario actualizado" : "Error al actualizar el usuario";
    }

    public ResponseEntity<Usuario> findUsuario(Integer id){
        Usuario usuario = usuarioRepo.findById(id).get();
        return ResponseEntity.ok(usuario);
    }
    public ResponseEntity<List<Usuario>> findALL(){
        List<Usuario> usuarios = usuarioRepo.findAll();
        return ResponseEntity.ok(usuarios);
    }

    public String findALLText(){
        List<Usuario> usuarios = this.findALL().getBody();
        StringBuilder texto = new StringBuilder();
        for (Usuario usuario : usuarios) {
            texto.append(usuario.toString());
        }
        return texto.toString();
    }
}
