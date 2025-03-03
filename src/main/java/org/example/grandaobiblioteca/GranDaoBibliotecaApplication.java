package org.example.grandaobiblioteca;

import jakarta.xml.bind.JAXBException;
import org.example.grandaobiblioteca.repositorio.TXTLibro;
import org.example.grandaobiblioteca.repositorio.XMLLibro;
import org.example.grandaobiblioteca.servicio.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.example.grandaobiblioteca.repositorio")
public class GranDaoBibliotecaApplication {

    public static void main(String[] args) throws IOException, JAXBException {
        SpringApplication.run(GranDaoBibliotecaApplication.class, args);
    }

}
