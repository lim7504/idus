package com.example.idus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
public class IdusApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdusApplication.class, args);
    }

}
