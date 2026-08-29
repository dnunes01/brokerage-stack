# brokerage-stack

[![Build](https://github.com/dnunes01/brokerage-stack/actions/workflows/build.yml/badge.svg)](https://github.com/dnunes01/brokerage-stack/actions/workflows/build.yml)

A Spring Boot REST API for tracking brokerage account holdings — built as a hands-on
portfolio project while I prep for backend/full-stack SWE roles.

## What this is

A from-scratch REST service modeling brokerage holdings (symbol, quantity, cost basis),
built incrementally to demonstrate applied Java/Spring skills beyond algorithm practice,
starting with API design.

**Status: working read/create API over an in-memory store.** `GET` and `POST` on
`/api/v1/holdings` are live, with bean validation on incoming payloads. Data lives in a
`ConcurrentHashMap` and is seeded at startup, so it resets on every restart — real
persistence, `PUT`/`DELETE`, and meaningful tests are next.

## Stack

- Java 21
- Spring Boot 3.5.16 (Spring Web, Bean Validation)
- Maven

Planned as the project grows: Swagger/OpenAPI docs, JUnit 5 + Mockito + Spring Test,
JPA persistence (H2/PostgreSQL), Docker, and a light AWS deployment.

## Running it

```
./mvnw spring-boot:run
```

Then hit the health check:

```
curl http://localhost:8080/alive
```

## API

| Method | Path                | Description                        | Success |
| ------ | ------------------- | ---------------------------------- | ------- |
| `GET`  | `/alive`            | Health check, returns `I am alive!` | `200`   |
| `GET`  | `/api/v1/holdings`  | List all holdings                  | `200`   |
| `GET`  | `/api/v1/holdings/{id}` | Fetch a single holding by id   | `200` / `404` |
| `POST` | `/api/v1/holdings`  | Create a holding                   | `201`   |

The store seeds two holdings on startup, so a fresh `GET` returns:

```
curl http://localhost:8080/api/v1/holdings
```

```json
[{"id":1,"symbol":"AAPL","quantity":"10","costBasis":"150.00"},
 {"id":2,"symbol":"MSFT","quantity":"5","costBasis":"320.50"}]
```

Creating one:

```
curl -X POST http://localhost:8080/api/v1/holdings \
  -H "Content-Type: application/json" \
  -d '{"symbol":"NVDA","quantity":3,"costBasis":"890.25"}'
```

`quantity` and `costBasis` are `BigDecimal` and serialize as JSON **strings** (via
`@JsonFormat(shape = STRING)`) to avoid floating-point precision loss in clients that
parse numbers as doubles. `costBasis` is returned scaled to 2 decimal places.

### Validation

`POST` payloads are validated with `@Valid`:

- `symbol` — required, non-blank
- `quantity` — required, must be positive
- `costBasis` — required, must be positive
- `id` — must be omitted; the server assigns it

## Roadmap

- [x] `GET`/`POST` endpoints for holdings
- [x] Bean validation on request payloads
- [ ] `PUT`/`DELETE` endpoints for holdings
- [ ] Persistence via Spring Data JPA (replacing the in-memory store)
- [ ] Swagger/OpenAPI documentation
- [ ] Unit + integration tests (JUnit 5, Mockito, Spring Test) — currently only a
      `contextLoads` smoke test
- [ ] Dockerize
- [ ] Deploy (AWS, stretch goal)
