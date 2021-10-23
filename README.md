# spring-rest-service

## Overview

A web service written using Spring Boot.

This project also contains unit and integration tests for each endpoint. Endpoint documentation in this service are shown in the Swagger UI screenshot below (reachable via http://localhost:8080/swagger-ui/index.html):

![image](https://user-images.githubusercontent.com/5192095/135779414-0f2acae4-11bb-4fc0-bfc5-002ee2f82dfd.png)

## How to Run

- Run using your favorite IDE
- Build and run the jar file from the project root directory:
    - `./mvnw package` (this will create the jar file in the `target` directory)
    - `java -jar target/spring-rest-service-N.N.N-SNAPSHOT.jar`
