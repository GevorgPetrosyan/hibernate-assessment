package com.egs.hibernate.assigment.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan({"com.egs.hibernate.assigment.rest.*", "com.egs.hibernate.assigment.core.*"})
@EnableJpaRepositories(basePackages = {"com.egs.hibernate.assigment.core.repository"})
@EntityScan("com.egs.hibernate.assigment.core.entity")
@OpenAPIDefinition(info = @Info(
        title = "Hibernate Assignment API", version = "0.0.1", description = "Hibernate Assignment Information"
))
public class HibernateAssigmentRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateAssigmentRestApplication.class, args);
    }

}
