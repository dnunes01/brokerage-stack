# brokerage-stack

[![Build](https://github.com/dnunes01/brokerage-stack/actions/workflows/build.yml/badge.svg)](https://github.com/dnunes01/brokerage-stack/actions/workflows/build.yml)

A Spring Boot REST API for tracking brokerage account holdings — built as a hands-on
portfolio project while I prep for backend/full-stack SWE roles.

## What this is

A from-scratch REST service modeling brokerage holdings (symbol, quantity, cost basis),
built incrementally to demonstrate applied Java/Spring skills beyond algorithm practice,
starting with API design.

**Status: full CRUD API over an in-memory store.** `GET`, `POST`, `PUT`, and `DELETE`
on `/api/v1/holdings` are live, with bean validation on incoming payloads and MockMvc
tests covering `PUT`. Data lives in a `ConcurrentHashMap` and is seeded at startup, so it
resets on every restart — broader test coverage and real persistence are next.

## Stack

- Java 21
- Spring Boot 3.5.16 (Spring Web, Bean Validation)
- Maven
- JUnit 5, Mockito, Spring Test (MockMvc)

Planned as the project grows: Swagger/OpenAPI docs, JPA persistence (H2/PostgreSQL),
Docker, and a light AWS deployment.

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
| `POST` | `/api/v1/holdings`  | Create a holding; `Location` header points to the new resource | `201` / `400` |
| `PUT`  | `/api/v1/holdings/{id}` | Replace an existing holding    | `200` / `404` / `400` |
| `DELETE` | `/api/v1/holdings/{id}` | Delete a holding (no response body) | `204` / `404` |

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

`POST` and `PUT` payloads are validated with `@Valid`; an invalid body returns `400`:

- `symbol` — required, non-blank
- `quantity` — required, must be positive
- `costBasis` — required, must be positive
- `id` — not part of the request body; the server assigns it (POST) or takes it from
  the path (PUT). An `id` sent in the body is ignored.

## Roadmap

- [x] `GET`/`POST` endpoints for holdings
- [x] Bean validation on request payloads
- [x] `PUT` endpoint for holdings
- [x] `DELETE` endpoint for holdings
- [ ] Persistence via Spring Data JPA (replacing the in-memory store)
- [ ] Swagger/OpenAPI documentation
- [ ] Unit + integration tests (JUnit 5, Mockito, Spring Test) — MockMvc tests cover
      `PUT` (200 / 404 / 400); `GET`, `POST`, `DELETE`, and `HoldingStore` tests are next
- [ ] Dockerize
- [ ] Deploy (AWS, stretch goal)

## How I use AI on this project

I'm building this with Claude Code, Anthropic's AI coding assistant, as a tutor and pair
programmer: it helps me plan each step, explains the Java and Spring concepts behind it, and
reviews what I write. I type all of the code myself and run every test and request locally,
so each commit reflects what I understood well enough to write. I'm noting it openly because
using these tools well is part of how I work, and because I want the learning to stay mine.
