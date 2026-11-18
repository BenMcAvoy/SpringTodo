# SpringTodo

SpringTodo is a small Java todo-list experiment built with Spring Boot. It has a Thymeleaf page for adding, completing and deleting items, plus a JSON API backed by Spring Data JPA and Flyway migrations.

## Run locally

You will need Java 21 and Docker. Start the single-node CockroachDB instance defined in Compose:

```sh
docker compose up -d
```

Then start the application:

```sh
./mvnw spring-boot:run
```

On Windows, run `mvnw.cmd spring-boot:run`. The page is served by the Spring application; the database connection settings are in `src/main/resources/application.properties`.

## API

- `GET /todos` - list todos.
- `POST /todos` - create a todo from a JSON body containing `title`.
- `POST /todos/{id}/complete` - update completion state.
- `DELETE /todos/{id}` - remove a todo.

This is a learning project and its database configuration is intended for local development.
