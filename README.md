# Alquilaria

A rental housing management application built in Java, using MySQL for data storage and a console-based interface following the MVC (Model-View-Controller) architecture pattern.

---

## Description

Alquilaria allows a real estate rental company to manage all its operational data: property owners, rental properties, tenants, and contracts. The application provides full CRUD operations for each entity, advanced statistics, automatic contract expiration handling, and data export in JSON and CSV formats.

---

## Technology Stack

| Component | Technology            |
|---|-----------------------|
| Language | Java (JDK 23)         |
| Database | MySQL 8+ (InnoDB)     |
| DB Connectivity | JDBC                  |
| IDE | IntelliJ IDEA |
| Testing | JUnit 5               |

---

## Project Structure

```
Alquilaria/
├── src/
│   ├── Main.java
│   ├── config.properties
│   ├── ConexionBD/
│   │   ├── Configuracion.java
│   │   └── ConexionBD.java
│   ├── Modelo/
│   │   ├── Propietario.java
│   │   ├── Vivienda.java
│   │   ├── Inquilino.java
│   │   └── Contrato.java
│   ├── DAO/
│   │   ├── PropietarioDAO.java
│   │   ├── ViviendaDAO.java
│   │   ├── InquilinoDAO.java
│   │   ├── ContratoDAO.java
│   │   └── EstadisticaDAO.java
│   ├── Controlador/
│   │   ├── ControladorPropietario.java
│   │   ├── ControladorVivienda.java
│   │   ├── ControladorInquilino.java
│   │   ├── ControladorContrato.java
│   │   └── ControladorEstadisticas.java
│   └── Vista/
│       ├── VistaPropietario.java
│       ├── VistaVivienda.java
│       ├── VistaInquilino.java
│       ├── VistaContrato.java
│       └── VistaEstadisticas.java
├── test/
│   ├── config.properties
│   ├── TestPropietarioDAO.java
│   ├── TestViviendaDAO.java
│   ├── TestInquilinoDAO.java
│   ├── TestContratoDAO.java
│   └── TestEstadisticaDAO.java
├── sql/
│   ├── Alquilaria_crea.sql
│   ├── Alquilaria_carga.sql
│   ├── Alquilaria_procedimientos.sql
│   └── Alquilaria_triggers.sql
└── README.md
```

---

## Database Setup

1. Run the scripts in this order:

```bash
mysql -u alumno -p < sql/Alquilaria_crea.sql
mysql -u alumno -p < sql/Alquilaria_carga.sql
mysql -u alumno -p < sql/Alquilaria_procedimientos.sql
mysql -u alumno -p < sql/Alquilaria_triggers.sql
```

This creates the `alquilaria_bd` database, loads sample data, and sets up all stored procedures. It also creates a dedicated user `mantenimiento` with the minimum required privileges (SELECT, INSERT, UPDATE, DELETE, EXECUTE).

The database contains the following tables:

| Table | Description |
|---|---|
| `propietario` | Property owners |
| `vivienda` | Rental properties |
| `inquilino` | Tenants |
| `contrato` | Rental contracts |
| `contrato_history` | Audit log — automatically records every INSERT, UPDATE and DELETE on `contrato`, storing the changed row along with the type of operation (`log_cambio`) and the timestamp (`log_ultima_modificacion`) |

2. Edit `src/config.properties` with your database connection details:

```properties
db.url=jdbc:mysql://localhost:3306/alquilaria_bd
db.user=mantenimiento
db.password=mantenimiento1234
```

---

## How to Run

Compile and run `Main.java`. On startup the application automatically marks any contracts whose end date has already passed as `vencido` (expired).

The main menu allows navigation to each management section:

```
╔══════════════════════════════════╗
║         ALQUILARIA - MENÚ        ║
╠══════════════════════════════════╣
║  1. Propietarios                 ║
║  2. Viviendas                    ║
║  3. Inquilinos                   ║
║  4. Contratos                    ║
║  5. Estadísticas                 ║
║  0. Salir                        ║
╚══════════════════════════════════╝
```

---

## Features

### Owner management
CRUD owners. Deleting an owner cascades to their properties and associated contracts.

### Property management
CRUD rental properties. Each property is linked to an owner and classified as `apartamento`, `atico`, or `casa`. Deleting a property cascades to its contracts.

### Tenant management
CRUD tenants. Tracks whether a tenant has a pet, which is relevant when matching tenants to pet-friendly properties.

### Contract management
CRUD contracts. Contracts start with `pendiente` status and can be manually changed to `activo` or `vencido`. The application also updates expired contracts automatically on startup. Every change to a contract is automatically logged in the `contrato_history` table via database triggers.

### Statistics and advanced queries
- Rental history for a given tenant (all past and current contracts).
- Active rentals for a given owner (properties with an active contract right now).
- Free properties (properties with no active contract at this moment).

### Data export
Each statistics query can be exported to:
- **JSON** — generated via MySQL `JSON_ARRAYAGG` and `JSON_OBJECT` functions, saved to the Downloads folder.
- **CSV** — generated from the in-memory result list using `PrintWriter` with UTF-8 encoding, saved to the Downloads folder.

---

## Architecture

The application follows a three-layer MVC pattern:

- **Model** — Plain Java classes (`Propietario`, `Vivienda`, `Inquilino`, `Contrato`) representing database entities.
- **DAO** — Static classes that handle all database communication via JDBC `CallableStatement`, calling stored procedures exclusively. Methods return objects or primitive values instead of printing to the console.
- **Controller** — Thin layer between the View and the DAO. Receives raw input parameters, builds model objects, and delegates to the DAO.
- **View** — Console menus that read user input using `Scanner.nextLine()` exclusively (no `nextInt()` or `nextDouble()` to avoid buffer issues) and display results.

All database logic is encapsulated in stored procedures. The application connects using a least-privilege database user, not root.

---

## Design Decisions

- **Stored procedures for all DB operations** — business rules (CHECK constraints, cascading deletes, state validation) live in the database layer, keeping Java code clean.
- **Audit table `contrato_history`** — a database trigger automatically logs every change to the `contrato` table, providing a full history of all contract modifications without any extra Java code.
- **Consistent error codes** — DAOs return `int` codes (`0` = success, `-1` = generic error, `-2` = validation error) or the generated ID on insert, instead of printing messages directly.
- **Single Scanner per class** — each View declares one `static final Scanner` shared across all its methods to avoid resource leaks and buffer conflicts.
- **config.properties** — connection parameters are kept outside source code for easy deployment configuration.