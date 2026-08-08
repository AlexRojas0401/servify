# Servify

Backend de **Servify**, una aplicación para conectar clientes con profesionales de servicios como fontanería, electricidad, etc.

El proyecto está actualmente en desarrollo.

---

## Requisitos

Antes de empezar, necesitas tener instalado:

* Java 21
* Docker Desktop
* Git

Puedes comprobar las versiones con:

```bash
java -version
docker --version
docker compose version
git --version
```

### Tecnologías utilizadas

* Java 21
* Spring Boot 4.1.0
* PostgreSQL 17
* Spring Data JPA
* Flyway
* Maven
* Docker
* Spring Security
* Jakarta Validation
* Lombok

---

# Clonar el proyecto

Clona el repositorio:

```bash
git clone https://github.com/AlexRojas0401/servify.git
```

Entra en el proyecto:

```bash
cd servify
```

La estructura principal es:

```text
servify/
├── README.md
├── .gitignore
└── backend/
    ├── src/
    ├── pom.xml
    ├── docker-compose.yml
    └── mvnw
```

---

# Base de datos

El proyecto utiliza **PostgreSQL 17 mediante Docker**.

Entra en la carpeta del backend:

```bash
cd backend
```

Levanta PostgreSQL:

```bash
docker compose up -d
```

Comprueba que el contenedor está funcionando:

```bash
docker ps
```

Deberías ver el contenedor:

```text
servify-postgres
```

## Datos de PostgreSQL

Para desarrollo local se utilizan:

```text
Host:           localhost
Puerto:         5432
Base de datos:  servify
Usuario:        servify
Contraseña:     servify123
```

Estos datos están definidos en:

```text
backend/docker-compose.yml
```

---

# Arrancar el backend

Desde la carpeta `backend`:

## macOS / Linux

```bash
./mvnw spring-boot:run
```

## Windows

```cmd
mvnw.cmd spring-boot:run
```

Spring Boot se iniciará en:

```text
http://localhost:8080
```

Si todo funciona correctamente, aparecerá:

```text
Tomcat started on port 8080
Started BackendApplication
```

---

# Base de datos y Flyway

No es necesario crear las tablas manualmente.

Al arrancar Spring Boot, **Flyway comprueba el estado de la base de datos y ejecuta automáticamente las migraciones pendientes**.

Las migraciones se encuentran en:

```text
backend/src/main/resources/db/migration/
```

Actualmente existen:

```text
V1__create_roles.sql
V2__create_user_statuses.sql
V3__enable_pgcrypto.sql
V4__create_users.sql
V5__create_professional_profiles.sql
V6__create_categories.sql
V7__create_services.sql
V8__insert_default_categories.sql
V9__create_professional_service_areas.sql
V10__create_request_statuses.sql
V11__create_service_requests.sql
```

Si la base de datos está vacía, Flyway ejecutará todas las migraciones automáticamente.

Por ejemplo:

```text
V1 → V2 → V3 → ... → V11
```

---

# Configuración de Spring Boot

La configuración se encuentra en:

```text
backend/src/main/resources/application.yml
```

La conexión utilizada es:

```text
jdbc:postgresql://localhost:5432/servify
```

con:

```text
Usuario:    servify
Contraseña: servify123
```

Hibernate está configurado con:

```text
ddl-auto: validate
```

Esto significa que Hibernate **no crea ni modifica las tablas de la base de datos**.

La estructura de la base de datos es gestionada por **Flyway**.

---

# Endpoint de registro

Actualmente está disponible el endpoint:

```http
POST /api/auth/register
```

URL completa:

```text
http://localhost:8080/api/auth/register
```

Ejemplo de petición:

```json
{
  "firstName": "Alejandro",
  "lastName": "Rojas",
  "email": "alejandro@test.com",
  "phone": "600123123",
  "password": "password123"
}
```

Si el registro es correcto, la API devuelve:

```text
201 Created
```

---

# Detener el proyecto

Para detener Spring Boot:

```text
Ctrl + C
```

Para detener PostgreSQL:

```bash
docker compose down
```

Esto elimina el contenedor, pero **mantiene los datos de PostgreSQL** almacenados en el volumen de Docker.

Al volver a ejecutar:

```bash
docker compose up -d
```

la base de datos conservará los datos anteriores.

---

# Eliminar completamente la base de datos de Docker

⚠️ **ATENCIÓN:** este comando elimina todos los datos de desarrollo almacenados en PostgreSQL.

Para eliminar el contenedor y también el volumen:

```bash
docker compose down -v
```

Esto deja la base de datos completamente limpia.

Después puedes crear una nueva:

```bash
docker compose up -d
```

Y arrancar Spring Boot:

```bash
./mvnw spring-boot:run
```

Flyway detectará que la base de datos está vacía y ejecutará automáticamente todas las migraciones desde `V1` hasta la última versión disponible.

## Comprobar los volúmenes de Docker

Puedes consultar los volúmenes existentes:

```bash
docker volume ls
```

Debería aparecer un volumen asociado a PostgreSQL, normalmente:

```text
servify_postgres_data
```

No es necesario eliminar manualmente el volumen.

Para reiniciar completamente la base de datos de Servify utiliza:

```bash
docker compose down -v
```

---

# Actualizar el proyecto

Antes de empezar a trabajar, descarga los últimos cambios:

```bash
git pull
```

Después comprueba que PostgreSQL está funcionando:

```bash
docker compose up -d
```

Y arranca el backend:

```bash
./mvnw spring-boot:run
```

Si existen nuevas migraciones de Flyway, se ejecutarán automáticamente.

---

# Flujo de trabajo con Git

## Comprobar cambios

```bash
git status
```

## Añadir cambios

Para añadir todos los cambios:

```bash
git add .
```

O un archivo concreto:

```bash
git add nombre-del-archivo
```

## Crear un commit

```bash
git commit -m "Descripción del cambio"
```

Ejemplo:

```bash
git commit -m "Add authentication register endpoint"
```

## Subir cambios a GitHub

```bash
git push
```

## Descargar cambios de otros desarrolladores

```bash
git pull
```

---

# Flujo recomendado de trabajo

Cada vez que empieces a trabajar:

```bash
git pull
```

Comprueba que Docker está funcionando:

```bash
docker compose up -d
```

Arranca el backend:

```bash
./mvnw spring-boot:run
```

Después de realizar cambios:

```bash
git status
git add .
git commit -m "Descripción del cambio"
git push
```

---

# Estructura del backend

```text
backend/
├── pom.xml
├── docker-compose.yml
├── mvnw
├── mvnw.cmd
│
└── src/
    ├── main/
    │   ├── java/com/servify/backend/
    │   │
    │   ├── application/
    │   │   ├── controller/
    │   │   └── service/
    │   │
    │   ├── domain/
    │   │   ├── dto/
    │   │   │   ├── request/
    │   │   │   └── response/
    │   │   │
    │   │   ├── entity/
    │   │   └── repository/
    │   │
    │   ├── infrastructure/
    │   │   └── config/
    │   │
    │   └── web/
    │       └── controller/
    │
    └── resources/
        ├── db/
        │   └── migration/
        └── application.yml
```

---

# Estado actual del proyecto

Actualmente el backend incluye:

* Registro de usuarios
* Usuarios
* Roles
* Estados de usuario
* Perfiles profesionales
* Categorías
* Servicios
* Áreas de servicio profesionales
* Solicitudes de servicio
* Estados de solicitudes
* PostgreSQL mediante Docker
* Migraciones de base de datos mediante Flyway
* Validación de peticiones
* Spring Security configurado
* API REST

---

# Desarrollo

El proyecto está en desarrollo y se irán incorporando nuevas funcionalidades progresivamente.

La base de datos debe modificarse mediante **migraciones Flyway**, evitando realizar cambios manuales en la estructura de PostgreSQL.

Las nuevas migraciones deben seguir el formato:

```text
V12__descripcion.sql
V13__descripcion.sql
V14__descripcion.sql
```

y deben almacenarse en:

```text
backend/src/main/resources/db/migration/
```
