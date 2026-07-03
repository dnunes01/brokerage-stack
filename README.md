# brokerage-stack

A Spring Boot REST API for tracking brokerage account holdings — built as a hands-on
portfolio project while I prep for backend/full-stack SWE roles.

## What this is

A from-scratch REST service modeling brokerage holdings (symbol, quantity, cost basis),
built incrementally to demonstrate applied Java/Spring skills beyond algorithm practice:
API design, persistence, testing, and containerization/deployment.

**Status: early scaffold.** Right now there's a `Holding` domain class and a single
health-check endpoint. CRUD endpoints, persistence, and tests are next.

## Stack

- Java 21
- Spring Boot 3.5.16 (Spring Web)
- Maven

Planned as the project grows: Spring MVC REST endpoints (full CRUD on holdings),
Swagger/OpenAPI docs, JUnit 5 + Mockito + Spring Test, JPA persistence (H2/PostgreSQL),
Docker, and a light AWS deployment.

## Running it

```
./mvnw spring-boot:run
```

Then hit the health check:

```
curl http://localhost:8080/alive
```

## Roadmap

- [ ] CRUD endpoints for holdings (`GET/POST/PUT/DELETE /holdings`)
- [ ] Bean validation on request payloads
- [ ] Persistence via Spring Data JPA
- [ ] Swagger/OpenAPI documentation
- [ ] Unit + integration tests (JUnit 5, Mockito, Spring Test)
- [ ] Dockerize
- [ ] Deploy (AWS, stretch goal)
