-- Creacion de la base de datos 
DROP DATABASE IF EXISTS alquilaria_bd;
CREATE DATABASE alquilaria_bd;

USE alquilaria_bd;

-- Creacion de la tabla Propietario
CREATE TABLE propietario (
id INT AUTO_INCREMENT PRIMARY KEY,
DNI VARCHAR(11) NOT NULL UNIQUE,
nombre VARCHAR(150) NOT NULL,
correo_electronico VARCHAR(200),
telefono VARCHAR(11)
);

-- Creacion de la tabla Vivienda
CREATE TABLE vivienda (
id VARCHAR(10) PRIMARY KEY,
id_propietario INT NOT NULL,
direccion VARCHAR(100) NOT NULL,
alquiler_mensual DECIMAL(10,2) NOT NULL,
superficie DECIMAL(10,2) NOT NULL,
descripcion VARCHAR(500),
permite_mascota BOOL NOT NULL,
tipo VARCHAR(15) NOT NULL,
CONSTRAINT fk_vivienda_propietario FOREIGN KEY(id_propietario) REFERENCES propietario(id),
-- Restricciones tipo check para no meter datos invalidos
CONSTRAINT ck_vivienda_alquiler_mensual CHECK(alquiler_mensual > 0),
CONSTRAINT ck_vivienda_superficie CHECK(superficie > 0),
CONSTRAINT ck_vivienda_tipo CHECK(tipo IN ('apartamento', 'atico', 'casa'))
);


-- Creacion de la tabla Inquilino
CREATE TABLE inquilino (
id INT AUTO_INCREMENT PRIMARY KEY,
DNI VARCHAR(11) NOT NULL UNIQUE KEY,
nombre VARCHAR(150) NOT NULL,
correo_electronico VARCHAR(200),
telefono VARCHAR(11),
tiene_mascota BOOL NOT NULL
);


-- Creacion de la tabla Contrata
CREATE TABLE contrato (
id INT AUTO_INCREMENT PRIMARY KEY,
id_vivienda VARCHAR(10) NOT NULL,
id_inquilino INT NOT NULL,
fecha_inicio DATE NOT NULL,
fecha_fin DATE NOT NULL,
precio DECIMAL(10,2) NOT NULL,
estado VARCHAR(10) NOT NULL DEFAULT 'pendiente',
CONSTRAINT ck_contrata_precio CHECK(precio > 0),
CONSTRAINT ck_contrata_fecha CHECK(fecha_fin > fecha_inicio),
CONSTRAINT ck_contrata_estado CHECK(estado IN('pendiente', 'activo', 'vencido')),
CONSTRAINT fk_contrata_vivienda FOREIGN KEY(id_vivienda) REFERENCES vivienda(id),
CONSTRAINT fk_contrata_inquilino FOREIGN KEY(id_inquilino) REFERENCES inquilino(id)
);


-- Creacion del usuario 
DROP USER IF EXISTS mantenimiento@'%'; 
CREATE USER mantenimiento@'%' IDENTIFIED BY 'mantenimiento1234';
GRANT INSERT, SELECT, DELETE, UPDATE ON alquilaria_bd.* TO mantenimiento@'%';


