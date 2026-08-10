# Library App

Simple Spring Boot web application for managing library books, clients, and borrowings.

Repository:

`https://github.com/Gu-Master/Bookstore`

## Technologies

- JDK: `Java 1.8`
- IoC / Framework: `Spring Boot 2.7.18`
- Front: `Spring Web MVC` + `Thymeleaf`
- Database: `PostgreSQL 14`
- Build tool: `Maven`

## Implemented Features

- list of books
- add book: title, author, ISBN
- edit book
- list of clients
- add client: full name, birth date
- edit client
- borrowing form for taking a book to read
- REST endpoint with JSON report of all current borrowings

REST endpoint:

- `GET /api/readers`

Returned fields:

- client full name
- client birth date
- book title
- book author
- book ISBN
- borrowing date

## Required Setup

Install:

- JDK 8 or newer
- Maven
- PostgreSQL 14

Create an empty database:

```sql
CREATE DATABASE library_db;
```

The application reads configuration from environment variables:

- `DB_URL` default: `jdbc:postgresql://localhost:5432/library_db`
- `DB_USERNAME` default: `postgres`
- `DB_PASSWORD` default: `postgres`
- `SERVER_PORT` default: `8080`

Example for Windows PowerShell:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/library_db"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"
$env:SERVER_PORT="8080"
```

## Build

Compilation used by reviewers:

```bash
mvn package
```

Result artifact:

`target/library-app-0.0.1-SNAPSHOT.jar`

## Run

Run the packaged application:

```bash
java -jar target/library-app-0.0.1-SNAPSHOT.jar
```

## Database Schema

Database tables are created automatically on application startup by Hibernate.

Relevant setting:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
```

Important note:

- the PostgreSQL database itself must already exist
- tables and indexes are created automatically by the application

## Available Pages

- Home: `http://localhost:8080/`
- Books: `http://localhost:8080/books`
- Clients: `http://localhost:8080/clients`
- Borrowings: `http://localhost:8080/borrowings`
- REST API: `http://localhost:8080/api/readers`

## Notes About Performance

- paginated lists for books, clients, and borrowings
- unique constraint on ISBN
- indexes on borrowing table
- `EntityGraph` is used for borrowing list to avoid N+1 on web page rendering
- DTO projection is used for the REST report
