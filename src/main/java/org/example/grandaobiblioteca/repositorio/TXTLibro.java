package org.example.grandaobiblioteca.repositorio;

import org.example.grandaobiblioteca.entidad.Libro;
import org.example.grandaobiblioteca.entidad.LibroDTO;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TXTLibro {
    private List<Libro> libros;
    private File archivo = new File("./archivo.txt");

    public List<Libro> getLibros() throws IOException {
        archivo = new File(archivo.getAbsolutePath() + ".txt");
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
    private boolean persitir(List<Libro> libros) throws IOException {
        archivo = new File(archivo.getAbsolutePath() + ".txt");
        archivo.delete();
        archivo = new File("./archivo.txt");
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
        persitir(libros);
        return true;
    }
    public boolean eliminarLibro(String isbn) throws IOException {
        libros = getLibros();
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                libros.remove(libro);
                persitir(libros);
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
                persitir(libros);
                return true;
            }
        }
        return false;
    }

}
