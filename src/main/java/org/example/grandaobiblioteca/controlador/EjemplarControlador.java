package org.example.grandaobiblioteca.controlador;

import org.example.grandaobiblioteca.dto.EjemplarDto;
import org.example.grandaobiblioteca.dto.ListaEjemplar;
import org.example.grandaobiblioteca.entidad.Ejemplar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.grandaobiblioteca.servicio.ServicioEjemplar;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ejemplar")
public class EjemplarControlador {

    @Autowired()
    ServicioEjemplar servicioEjemplar;

    // 📥 Recibir y 📤 Devolver EjemplarDto en XML
    @GetMapping(value = "/XML", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ListaEjemplar getEjemplares() {

        return new ListaEjemplar(servicioEjemplar.obtenerEjemplares());
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
    public String agregarTexto(@Param(value = "texto") String texto){
        return servicioEjemplar.insertarEjemplarTexto(texto) == true ? texto : null;

    }
    @PutMapping(value = "/ejemplar/modificarTexto", consumes = "text/plain", produces = "text/plain")
    public String modificarTexto(@Param(value = "texto") String texto){
        return  servicioEjemplar.modificarEjemplarTexto(texto) == true ? texto : null;
    }
    public String eliminarTexto(@Param(value = "texto") String texto){
        return servicioEjemplar.eliminarEjemplarTexto(texto) == true ? texto : null;

    }




}
