package org.example.grandaobiblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.example.grandaobiblioteca.repositorio")
public class GranDaoBibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GranDaoBibliotecaApplication.class, args);
    }

}
