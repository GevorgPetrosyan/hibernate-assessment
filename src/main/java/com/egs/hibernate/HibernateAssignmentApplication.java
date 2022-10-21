package com.egs.hibernate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories
@OpenAPIDefinition(info = @Info(title = "Hibernate Assignment API", version = "0.0.1", description = "Hibernate Assignment Information"))

public class HibernateAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateAssignmentApplication.class, args);
    }
}
