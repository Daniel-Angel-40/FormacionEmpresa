-- Creacion de la base de datos 
DROP DATABASE IF EXISTS alquilaria_bd;
CREATE DATABASE alquilaria_bd;

USE alquilaria_bd;

-- Creacion de la tabla Propietario
CREATE TABLE propietario (
id INT AUTO_INCREMENT PRIMARY KEY,
DNI VARCHAR(11) UNIQUE KEY,
nombre VARCHAR(150) NOT NULL,
correo_electronico VARCHAR(200),
telefono VARCHAR(11)
);

-- Creacion de la tabla Vivienda
CREATE TABLE vivienda (
id VARCHAR(10),
id_propietario INT,
direccion VARCHAR(100) NOT NULL,
alquiler_mensual DECIMAL(10,2) NOT NULL,
superficie DECIMAL(10,2) NOT NULL,
descripcion VARCHAR(500),
permite_mascota BOOL NOT NULL,
tipo VARCHAR(15) NOT NULL,
PRIMARY KEY (id, id_propietario),
CONSTRAINT fk_vivienda_propietario FOREIGN KEY(id_propietario) REFERENCES propietario(id),
-- Restricciones tipo check para no meter datos invalidos
CONSTRAINT ck_vivienda_alquiler_mensual CHECK(alquiler_mensual >= 0),
CONSTRAINT ck_vivienda_superficie CHECK(alquiler_mensual > 0)
);


-- Creacion de la tabla Inquilino
CREATE TABLE inquilino (
id INT AUTO_INCREMENT PRIMARY KEY,
DNI VARCHAR(11) UNIQUE KEY,
nombre VARCHAR(150) NOT NULL,
correo_electronico VARCHAR(200),
telefono VARCHAR(11),
tiene_mascota BOOL NOT NULL
);


-- Creacion de la tabla Contrata
CREATE TABLE contrata (
id_vivienda VARCHAR(10),
id_propietario INT,
id_inquilino INT,
fecha_inicio DATE NOT NULL,
fecha_fin DATE NOT NULL,
precio DECIMAL(10,2) NOT NULL,
estado VARCHAR(10) NOT NULL DEFAULT 'Pendiente',
CONSTRAINT ck_contrata_precio CHECK(precio >= 0),
PRIMARY KEY(id_vivienda, id_propietario, id_inquilino),
CONSTRAINT fk_contrata_vivienda FOREIGN KEY(id_vivienda) REFERENCES vivienda(id),
CONSTRAINT fk_contrata_propietario FOREIGN KEY(id_propietario) REFERENCES vivienda(id_propietario),
CONSTRAINT fk_contrata_inquilino FOREIGN KEY(id_inquilino) REFERENCES inquilino(id)
);


-- Creacion del usuario 
CREATE USER mantenimiento@'%' IDENTIFIED BY 'mantenimiento1234';
GRANT INSERT, SELECT, DELETE, UPDATE ON alquilaria_bd.* TO mantenimiento@'%';


