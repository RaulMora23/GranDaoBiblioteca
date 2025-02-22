package servicio;

import dto.UsuarioDto;
import entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorio.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioUsuario {

    @Autowired
    UsuarioRepository repo;
    public boolean validarUsuario(UsuarioDto usuarioDto){

    }
    public UsuarioDto obtenerDTO(Usuario usuario){

    }
    public Usuario obtenerEntidad(UsuarioDto usuarioDto){
        return repo.findById(usuarioDto.getId()).get();
    }

    public List<UsuarioDto> obtenerUsuarioes(){
        List<UsuarioDto> lista = new ArrayList<>();
        repo.findAll().forEach(e->{
            lista.add(obtenerDTO(e));
        });
        return lista;
    }
    public boolean insertarUsuario(UsuarioDto usuarioDto){
        try {
            repo.save(obtenerEntidad(usuarioDto));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean eliminarUsuario(int id){
        try {
            repo.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean actualizarUsuario(UsuarioDto usuarioDto){
        try {
            repo.save(obtenerEntidad(usuarioDto));
        }catch (Exception e){}
    }
    
}
