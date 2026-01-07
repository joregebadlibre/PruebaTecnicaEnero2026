# Prueba Técnica - API

API REST construida con **Spring Boot** para la gestión de **Clientes**, **Cuentas**, **Movimientos** y **Reportes** (estado de cuenta por rango de fechas), usando **PostgreSQL**.

## Tecnologías

- **Java 17**
- **Spring Boot 3.3.5**
- **Spring Web / Spring Data JPA / Validation**
- **PostgreSQL**
- **OpenAPI 3** (especificación en `openapi.yaml` + generación de interfaces con `org.openapi.generator`)
- **Apache PDFBox** (generación de PDF para reporte)
- Docker / Docker Compose

## Estructura del proyecto

- `src/main/java`: código fuente de la aplicación
- `src/main/resources/application.yml`: configuración base (usa variables de entorno)
- `openapi.yaml`: contrato OpenAPI de la API
- `PruebaTecnica.postman_collection.json`: colección Postman para probar endpoints
- `postman_curls.sh`: script con ejemplos `curl`
- `docker-compose.yml`: levanta Postgres + la API
- `Dockerfile`: build multi-stage (Gradle + JRE)

## Pruebas Unitarias

- `src/test/java/com/pruebatecnica/controller/AccountControllerTest.java`
- `src/test/java/com/pruebatecnica/controller/CustomerControllerTest.java`
- `src/test/java/com/pruebatecnica/controller/ReportControllerTest.java`
- `src/test/java/com/pruebatecnica/controller/TransactionControllerTest.java`

## Requisitos

- Opción A (recomendada): **Docker** y **Docker Compose**
- Opción B (local): **Java 17** y **Gradle** (incluye wrapper `./gradlew`)

## Configuración (variables de entorno)

La app toma configuración desde `src/main/resources/application.yml`:

- `DB_URL` (default: `jdbc:postgresql://localhost:5432/pruebatecnica`)
- `DB_USERNAME` (default: `postgres`)
- `DB_PASSWORD` (default: `postgres`)
- `SERVER_PORT` (default: `8080`)

## Levantar con Docker Compose

Desde la raíz del repo:

```bash
docker compose up --build
```

Servicios:

- API: `http://localhost:8080`
- Postgres: `localhost:5432`
  - DB: `pruebatecnica`
  - user: `postgres`
  - pass: `postgres`

## Levantar local (sin Docker)

1) Levanta Postgres local (o ajusta las variables `DB_*`).

2) Ejecuta la app:

```bash
./gradlew bootRun
```

Si necesitas sobreescribir variables:

```bash
DB_URL=jdbc:postgresql://localhost:5432/pruebatecnica \
DB_USERNAME=postgres \
DB_PASSWORD=postgres \
SERVER_PORT=8080 \
./gradlew bootRun
```

## Contrato OpenAPI

- Archivo: `openapi.yaml`
- Base URL: `http://localhost:8080`

La generación de código OpenAPI se ejecuta automáticamente antes de compilar (`compileJava` depende de `openApiGenerate`).

## Colección Postman

Importa en Postman:

- `PruebaTecnica.postman_collection.json`

Variables:

- `baseUrl`: `http://localhost:8080`

La colección guarda automáticamente `customerId`, `accountId` y `transactionId` cuando creas recursos.

## Ejemplos con curl

Ejecuta:

```bash
bash postman_curls.sh
```

## Endpoints principales

Base URL: `http://localhost:8080`

### Customers

- `GET /customers`
- `POST /customers`
- `GET /customers/{id}`
- `PUT /customers/{id}`
- `DELETE /customers/{id}`
- `PATCH /customers/{id}/status?active={true|false}`

### Accounts

- `GET /accounts`
- `GET /accounts?customerId={customerId}`
- `POST /accounts`
- `GET /accounts/{id}`
- `PUT /accounts/{id}`
- `DELETE /accounts/{id}`

### Transactions

- `GET /transactions`
- `POST /transactions`
- `GET /transactions/{id}`
- `PUT /transactions/{id}` (solo permite actualizar la **última transacción** por cuenta)
- `DELETE /transactions/{id}`

### Reports

- `GET /reports?customerId={id}&from=YYYY-MM-DD&to=YYYY-MM-DD`

Respuesta: JSON con el estado de cuenta y un `pdfBase64`.
