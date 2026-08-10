# Library App

Веб-приложение для учета книг, клиентов и выдач в библиотеке.

Репозиторий: `https://github.com/Gu-Master/Bookstore`

## Технологии

- Java 1.8
- Spring Boot 2.7.18
- Spring Web MVC
- Thymeleaf
- Spring Data JPA
- PostgreSQL 14
- Maven

## Функциональность

- просмотр списка книг
- добавление и редактирование книги
- просмотр списка клиентов
- добавление и редактирование клиента
- оформление выдачи книги клиенту
- REST API со списком выданных книг

REST API:

- `GET /api/readers`

Поля ответа:

- ФИО клиента
- дата рождения клиента
- наименование книги
- автор книги
- ISBN книги
- дата выдачи книги

## Настройка

Требуется:

- JDK 8 или новее
- PostgreSQL 14
- Maven

Необходимо создать базу данных:

```sql
CREATE DATABASE library_db;
```

Приложение использует переменные окружения:

- `DB_URL` по умолчанию: `jdbc:postgresql://localhost:5432/library_db`
- `DB_USERNAME` по умолчанию: `postgres`
- `DB_PASSWORD` по умолчанию: `postgres`
- `SERVER_PORT` по умолчанию: `8080`

Пример для Windows PowerShell:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/library_db"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"
$env:SERVER_PORT="8080"
```

## Сборка

```bash
mvn package
```

После сборки создается файл:

`target/library-app-0.0.1-SNAPSHOT.jar`

## Запуск

```bash
java -jar target/library-app-0.0.1-SNAPSHOT.jar
```

## База данных

Таблицы создаются автоматически при старте приложения.

Используемая настройка:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
```

База данных `library_db` должна существовать до запуска приложения.

## Доступные страницы

- главная: `http://localhost:8080/`
- книги: `http://localhost:8080/books`
- клиенты: `http://localhost:8080/clients`
- выдачи: `http://localhost:8080/borrowings`
- REST API: `http://localhost:8080/api/readers`

## Дополнительно

- для списков используется пагинация
- для ISBN установлено ограничение уникальности
- для таблицы выдач созданы индексы
