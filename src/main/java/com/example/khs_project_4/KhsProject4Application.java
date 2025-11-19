package com.example.khs_project_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KhsProject4Application {

    public static void main(String[] args) {
        SpringApplication.run(KhsProject4Application.class, args);
    }

}
