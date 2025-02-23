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

    // Metodos Por texto

    // 📥 Recibir y 📤 Devolver EjemplarDto en Texto Plano
    @GetMapping(value = "/Texto", produces = "text/plain")
    public String obtenerTexto() {
        List<EjemplarDto> lista = servicioEjemplar.obtenerEjemplares();
        StringBuilder texto = new StringBuilder();
        for (EjemplarDto ejemplar : lista){
            texto.append(ejemplar.toString());
            return texto.toString();
        }
        return texto.toString();
    }
    @PostMapping(value = "/Texto", consumes = "text/plain", produces = "text/plain")
    public EjemplarDto agregarTexto(String texto){
        String[] lineas = texto.split(",");
        EjemplarDto ejemplarDto = new EjemplarDto(Integer.parseInt(lineas[0]), lineas[1], lineas[2]);
        boolean valido = servicioEjemplar.insertarEjemplar(ejemplarDto);

        if(valido){
            return ejemplarDto;
        }else{
            return null;
        }
    }
    @PutMapping(value = "/Texto", consumes = "text/plain", produces = "text/plain")
    public EjemplarDto modificarTexto(String texto){
        String[] lineas = texto.split(",");
        EjemplarDto ejemplarDto = new EjemplarDto(Integer.parseInt(lineas[0]), lineas[1], lineas[2]);
        boolean valido = servicioEjemplar.actualizarEjemplar(ejemplarDto);
        if(valido){
            return ejemplarDto;
        }else{
            return null;
        }
    }
    public EjemplarDto eliminarTexto(String texto){
        String[] lineas = texto.split(",");
        EjemplarDto ejemplarDto = new EjemplarDto(Integer.parseInt(lineas[0]), lineas[1], lineas[2]);
        boolean valido = servicioEjemplar.eliminarEjemplar(ejemplarDto.getId());
        if (valido){
            return ejemplarDto;
        }else{
            return null;
        }
    }




}
