package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.Libro;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TXTLibro {
    private List<Libro> libros;
    private static File archivo;


    public List<Libro> getLibros() throws IOException {
        libros = new ArrayList<Libro>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        linea = br.readLine();
        while (linea != null) {
            String[] array = linea.split(",");
            Libro libro = new Libro(array[0], array[1], array[2]);
            libros.add(libro);
            linea = br.readLine();
        }
        br.close();
        return libros;
    }
    public boolean persistir(List<Libro> libros) throws IOException {
        if (archivo!=null) {
            archivo.delete();
        }
        archivo = new File("./libros.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        for (Libro libro : libros) {
            bw.write(libro.toString());
        }
        bw.close();
        return true;
    }

    public boolean agregarLibro(Libro libro) throws IOException {
        libros = getLibros();
        libros.add(libro);
        persistir(libros);
        return true;
    }
    public boolean eliminarLibro(String isbn) throws IOException {
        libros = getLibros();
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                libros.remove(libro);
                persistir(libros);
                return true;
            }
        }
        return false;
    }
    public boolean actualizarLibro(Libro libro) throws IOException {
        libros = getLibros();
        for (Libro libro2 : libros) {
            if(libro2.getIsbn().equals(libro.getIsbn())) {
                libros.set(libros.indexOf(libro2), libro);
                persistir(libros);
                return true;
            }
        }
        return false;
    }

}
