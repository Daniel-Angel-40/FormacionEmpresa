-- Script de carga
USE alquilaria_bd;

-- Tabla propietario
INSERT INTO propietario (DNI, nombre, correo_electronico, telefono)
VALUES
('12345678A', 'Juan Pérez García', 'juan.perez@example.com', '600111111'),
('23456789B', 'María López Sánchez', 'maria.lopez@example.com', '600222222'),
('34567890C', 'Carlos Martínez Ruiz', 'carlos.martinez@example.com', '600333333'),
('45678901D', 'Ana Fernández Torres', 'ana.fernandez@example.com', '600444444'),
('56789012E', 'Luis Gómez Navarro', 'luis.gomez@example.com', '600555555'),
('67890123F', 'Elena Díaz Romero', 'elena.diaz@example.com', '600666666'),
('78901234G', 'Javier Moreno Castillo', 'javier.moreno@example.com', '600777777'),
('89012345H', 'Lucía Herrera Molina', 'lucia.herrera@example.com', '600888888'),
('90123456I', 'David Sánchez Ortega', 'david.sanchez@example.com', '600999999'),
('01234567J', 'Sara Jiménez Vega', 'sara.jimenez@example.com', '600123456');