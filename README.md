# Library App

Простое веб-приложение для библиотеки на `Java 8`, `Spring Boot 2.7`, `Spring Web`, `Spring Data JPA`, `Thymeleaf`, `PostgreSQL 14`, сборка через `Maven`.

## Что реализовано

- список книг
- добавление и редактирование книги
- список клиентов
- добавление и редактирование клиента
- интерфейс выдачи книги клиенту
- REST endpoint с JSON-списком всех выдач: `GET /api/readers`

## Технологии

- JDK: `1.8`
- Spring Boot: `2.7.18`
- Database: `PostgreSQL 14`
- Build tool: `Maven`

## Настройки

Приложение читает настройки из переменных окружения:

- `DB_URL` - JDBC URL, по умолчанию `jdbc:postgresql://localhost:5432/library_db`
- `DB_USERNAME` - пользователь БД, по умолчанию `postgres`
- `DB_PASSWORD` - пароль БД, по умолчанию `postgres`
- `SERVER_PORT` - порт приложения, по умолчанию `8080`

Пример для Windows PowerShell:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/library_db"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"
```

## Подготовка PostgreSQL

1. Установить PostgreSQL 14.
2. Создать пустую базу данных `library_db`.
3. Убедиться, что логин и пароль совпадают с переменными окружения.

Пример создания БД:

```sql
CREATE DATABASE library_db;
```

Схема БД создается автоматически при старте приложения за счет настройки:

```yaml
spring.jpa.hibernate.ddl-auto=update
```

## Сборка и запуск

Сборка:

```bash
mvn package
```

Запуск:

```bash
java -jar target/library-app-0.0.1-SNAPSHOT.jar
```

После запуска будут доступны:

- Web UI: `http://localhost:8080/`
- Книги: `http://localhost:8080/books`
- Клиенты: `http://localhost:8080/clients`
- Выдачи: `http://localhost:8080/borrowings`
- REST API: `http://localhost:8080/api/readers`

## Что будет интересно на проверке

- списки сделаны с пагинацией по 20 записей
- на ISBN стоит уникальное ограничение
- для таблицы выдач добавлены индексы
- для web-списка выдач используется загрузка связанных сущностей без N+1
- для REST-отчета используется отдельная DTO-проекция вместо лишней загрузки сущностей

## GitHub

Код готов к публикации в GitHub-репозиторий. Из этой среды я не выполнял `git push`, потому что здесь не настроен ваш удаленный репозиторий и доступ.
