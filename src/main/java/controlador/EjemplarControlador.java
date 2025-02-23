package controlador;

import dto.EjemplarDto;
import entidad.Ejemplar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import servicio.ServicioEjemplar;

import java.util.List;

@RestController
@RequestMapping("/ejemplar")
public class EjemplarControlador {

    @Autowired
    ServicioEjemplar servicioEjemplar;

    // 📥 Recibir y 📤 Devolver EjemplarDto en XML
    @GetMapping(value = "/XML", produces = "application/xml")
    public List<EjemplarDto> obtenerXML(@RequestBody EjemplarDto ejemplar) {
        return servicioEjemplar.obtenerEjemplares();
    }

    // 📥 Recibir y 📤 Devolver EjemplarDto en JSON
    @GetMapping(value = "/JSON", produces = "application/json")
    public List<EjemplarDto> obtenerJSON() {
        return servicioEjemplar.obtenerEjemplares();
    }



    // 📤 Crear EjemplarDto en XML
    @PostMapping(value = "/XML", consumes = "application/xml", produces = "application/xml")
    public EjemplarDto crearXML(@RequestBody EjemplarDto ejemplar) {
        if(servicioEjemplar.validarEjemplar(ejemplar)){
            servicioEjemplar.insertarEjemplar(ejemplar);
            return ejemplar;
        }else
        return ejemplar;
    }

    // 📤 Crear EjemplarDto en JSON
    @PostMapping(value = "/JSON", consumes = "application/json", produces = "application/json")
    public EjemplarDto crearJSON(@RequestBody EjemplarDto ejemplar) {
        return ejemplar;
    }


    // 📤 Actualizar EjemplarDto en XML
    @PutMapping(value = "/XML", consumes = "application/xml", produces = "application/xml")
    public EjemplarDto actualizarXML(@RequestBody EjemplarDto ejemplar) {
        return ejemplar;
    }

    // 📤 Actualizar EjemplarDto en JSON
    @PutMapping(value = "/JSON", consumes = "application/json", produces = "application/json")
    public Ejemplar actualizarJSON(@RequestBody EjemplarDto ejemplar) {
        return servicioEjemplar.obtenerEntidad(ejemplar);
    }

    // 📤 Actualizar EjemplarDto en Texto Plano
    @PutMapping(value = "/Texto", consumes = "text/plain", produces = "text/plain")
    public String actualizarTexto(@RequestBody String texto) {
        return "Ejemplar en Texto (Actualizado): " + texto;
    }

    // 🗑️ Eliminar EjemplarDto por ID
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        return "Ejemplar con ID " + id + " eliminado";
    }

    // Metodos Por texto

    // 📥 Recibir y 📤 Devolver EjemplarDto en Texto Plano
    @GetMapping(value = "/Texto", produces = "text/plain")
    public String obtenerTexto() {
       return servicioEjemplar.obtenerEjemplarTexto();
    }
    @PostMapping(value = "/Texto", consumes = "text/plain", produces = "text/plain")
    public String agregarTexto(String texto){
        return servicioEjemplar.insertarEjemplarTexto(texto) == true ? texto : null;

    }
    @PutMapping(value = "/Texto", consumes = "text/plain", produces = "text/plain")
    public String modificarTexto(String texto){
        return  servicioEjemplar.modificarEjemplarTexto(texto) == true ? texto : null;
    }
    public String eliminarTexto(String texto){
        return servicioEjemplar.eliminarEjemplarTexto(texto) == true ? texto : null;

    }




}
