<h1 align="center"> Hibernate Assessment </h1> <br>

<p align="center">
  Simple Spring Boot application to master Hibernate skills.
</p>




## Requirements
The application can be run locally or in a docker container, the requirements for setup are listed below.


### Local
* [Java 11 SDK](https://www.oracle.com/java/technologies/downloads/#java11)
* [Maven](https://maven.apache.org/download.cgi)
* [PostgreSQL](https://www.postgresql.org/download/)
* [Docker](https://www.docker.com/get-docker)

## Quick Start
Make sure you have PostgreSQL database set up on you environment using credentials form __application.properties__
or use docker-compose to run dockerized database.

### Run Dockerized database

```bash
$ docker-compose up
```

### Run Local Server
```bash
$ mvn spring-boot:run
```

Application will run by default on port `8080`

Configure the port by changing `server.port` in __application.properties__

## API
[Swagger-UI](http://localhost:8080/swagger-ui/index.html) for localhost