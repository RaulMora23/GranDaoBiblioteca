package org.example.grandaobiblioteca.servicio;
import org.example.grandaobiblioteca.entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.grandaobiblioteca.repositorio.UsuarioMongoRepo;
import org.example.grandaobiblioteca.repositorio.UsuarioRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServicioUsuario {

    @Autowired
    UsuarioRepository repo;

    @Autowired
    UsuarioMongoRepo mongo;

    public boolean validarUsuario(Usuario Usuario){
        ArrayList<String> listaLetras = new ArrayList<>(Arrays.asList("T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"));
        String di = Usuario.getDni();
        String diModificado = Usuario.getDni();
        if (di.startsWith("X")) {
            diModificado = "0" + di.substring(1);
        }
        if (di.startsWith("Y")) {
            diModificado = "1" + di.substring(1);
        }
        if (di.startsWith("Z")) {
            diModificado = "2" + di.substring(1);
        }
        if (listaLetras.get(Integer.parseInt((String) diModificado.subSequence(0, 8)) % 23).equals(di.charAt(8) + "")) {
            return true;
        } else {
            return false;
        }
    }
    public Usuario obtenerDTO(Usuario usuario){
        return new Usuario(usuario.getId(), usuario.getDni(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword(),usuario.getTipo(),usuario.getPenalizacionHasta());
    }
    public Usuario obtenerEntidad(Usuario Usuario){
        return repo.findById(Usuario.getId()).get();
    }

    public List<Usuario> obtenerUsuarios(){
        List<Usuario> lista = new ArrayList<>();
        repo.findAll().forEach(e->{
            lista.add(obtenerDTO(e));
        });
        return lista;
    }
    public boolean insertarUsuario(Usuario Usuario){
        try {
       //     mongo.save(obtenerEntidad(Usuario));
            repo.save(obtenerEntidad(Usuario));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean eliminarUsuario(int id){
        try {
            mongo.deleteById(id);
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean actualizarUsuario(Usuario Usuario){
        try {
       //     mongo.save(obtenerEntidad(Usuario));
            repo.save(obtenerEntidad(Usuario));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    
}
