package org.example.grandaobiblioteca.repositorio;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.example.grandaobiblioteca.entidad.Libro;
import org.example.grandaobiblioteca.entidad.LibroDTO;
import org.example.grandaobiblioteca.entidad.ListaLibros;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLLibro {
    private File archivoXML = new File("./XMLLibro.xml");;

    // Constructor vacío
    public XMLLibro() {}


    // Método para obtener todos los libros del XML
    public List<LibroDTO> getLibros() throws JAXBException, IOException {
        archivoXML = new File("./XMLLibro.xml");
        JAXBContext contexto = JAXBContext.newInstance(ListaLibros.class);
        Unmarshaller unmarshaller = contexto.createUnmarshaller();

        // Deserializamos el archivo XML
        ListaLibros lista = (ListaLibros) unmarshaller.unmarshal(archivoXML);
        return lista.getLibros();
    }

    // Método para guardar un nuevo libro en el XML
    public boolean guardarLibro(Libro libro) throws JAXBException {
        archivoXML = new File("./XMLLibro.xml");
        JAXBContext contexto = JAXBContext.newInstance(ListaLibros.class);
        Unmarshaller unmarshaller = contexto.createUnmarshaller();
        Marshaller marshaller = contexto.createMarshaller();

        // Deserializamos el archivo
        ListaLibros lista = (ListaLibros) unmarshaller.unmarshal(archivoXML);
        List<LibroDTO> listaLibros = lista.getLibros();

        // Convertimos el objeto libro a libroDTO y lo agregamos a la lista
        LibroDTO libroDTO = new LibroDTO(libro);
        listaLibros.add(libroDTO);

        // Actualizamos la lista de libros
        lista.setLibros(listaLibros);

        // Guardamos la lista actualizada en el archivo
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(lista, archivoXML);

        return true;
    }

    // Método para eliminar un libro por su ISBN
    public boolean eliminarLibro(String Isbn) throws JAXBException {
        archivoXML = new File("./XMLLibro.xml");
        JAXBContext contexto = JAXBContext.newInstance(ListaLibros.class);
        Unmarshaller unmarshaller = contexto.createUnmarshaller();
        Marshaller marshaller = contexto.createMarshaller();

        // Deserializamos el archivo
        ListaLibros lista = (ListaLibros) unmarshaller.unmarshal(archivoXML);
        List<LibroDTO> listaLibros = lista.getLibros();

        // Buscamos el libro por su ISBN y lo eliminamos si lo encontramos
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getIsbn().equals(Isbn)) {
                // Borramos el libro
                listaLibros.remove(i);

                // Actualizamos la lista de libros
                lista.setLibros(listaLibros);

                // Guardamos la lista actualizada en el archivo
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(lista, archivoXML);

                return true;
            }
        }
        return false;
    }

    // Método para modificar un libro
    public boolean modificarLibro(Libro libro) throws JAXBException {
        archivoXML = new File("./XMLLibro.xml");
        JAXBContext contexto = JAXBContext.newInstance(ListaLibros.class);
        Unmarshaller unmarshaller = contexto.createUnmarshaller();
        Marshaller marshaller = contexto.createMarshaller();

        // Deserializamos el archivo
        ListaLibros lista = (ListaLibros) unmarshaller.unmarshal(archivoXML);
        List<LibroDTO> listaLibros = lista.getLibros();

        // Buscamos el libro por su ISBN y lo modificamos si lo encontramos
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getIsbn().equals(libro.getIsbn())) {
                // Actualizamos la información del libro en la lista
                listaLibros.set(i, new LibroDTO(libro));

                // Actualizamos la lista de libros
                lista.setLibros(listaLibros);

                // Guardamos la lista actualizada en el archivo
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(lista, archivoXML);

                return true;
            }
        }
        return false;
    }

    public void persistit(List<Libro> all) throws JAXBException {
        List<LibroDTO> listaLibros = new ArrayList<LibroDTO>();
        for (Libro libro : all) {
            listaLibros.add(new LibroDTO(libro));
        }
        if (archivoXML!=null) {
            archivoXML.delete();
        }
        archivoXML = new File("./XMLLibro.xml");
        JAXBContext contexto = JAXBContext.newInstance(ListaLibros.class);
        Marshaller marshaller = contexto.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        ListaLibros lista = new ListaLibros(listaLibros);
        marshaller.marshal(lista, archivoXML);

    }
}

