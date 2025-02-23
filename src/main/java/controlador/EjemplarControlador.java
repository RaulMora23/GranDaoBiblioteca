package controlador;

import dto.EjemplarDto;
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
    public EjemplarDto obtenerJSON() {
        return new EjemplarDto("Título JSON", "Autor JSON");
    }

    // 📥 Recibir y 📤 Devolver EjemplarDto en Texto Plano
    @GetMapping(value = "/Texto", produces = "text/plain")
    public String obtenerTexto() {
        servicioEjemplar.obtenerEjemplares();
        for (EjemplarDto e : servicioEjemplar.obtenerEjemplares()) {
            servicioEjemplar
        }
        return r;
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

    // 📤 Crear EjemplarDto en Texto Plano
    @PostMapping(value = "/Texto", consumes = "text/plain", produces = "text/plain")
    public String crearTexto(@RequestBody String texto) {
        return texto;
    }

    // 📤 Actualizar EjemplarDto en XML
    @PutMapping(value = "/XML", consumes = "application/xml", produces = "application/xml")
    public EjemplarDto actualizarXML(@RequestBody EjemplarDto ejemplar) {
        return ejemplar;
    }

    // 📤 Actualizar EjemplarDto en JSON
    @PutMapping(value = "/JSON", consumes = "application/json", produces = "application/json")
    public EjemplarDto actualizarJSON(@RequestBody EjemplarDto ejemplar) {
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
}
