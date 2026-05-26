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



-- Tabla Vivienda
INSERT INTO vivienda (
    id,
    id_propietario,
    direccion,
    alquiler_mensual,
    superficie,
    descripcion,
    permite_mascota,
    tipo
)
VALUES
('VIV001', 1, 'Calle Mayor 12, Madrid', 850.00, 75.50,
 'Apartamento céntrico con balcón y buena iluminación.', 1, 'apartamento'),

('VIV002', 2, 'Avenida del Puerto 45, Valencia', 1200.00, 110.00,
 'Ático moderno con terraza amplia y vistas al mar.', 1, 'atico'),

('VIV003', 3, 'Calle Luna 8, Sevilla', 700.00, 65.00,
 'Apartamento reformado cerca del centro histórico.', 0, 'apartamento'),

('VIV004', 4, 'Paseo de Gracia 101, Barcelona', 2500.00, 180.00,
 'Casa de lujo con jardín y piscina privada.', 1, 'casa'),

('VIV005', 5, 'Calle Real 22, Málaga', 950.00, 80.00,
 'Apartamento acogedor ideal para parejas.', 1, 'apartamento'),

('VIV006', 6, 'Calle Sol 14, Bilbao', 1500.00, 130.00,
 'Ático con excelente iluminación natural.', 0, 'atico'),

('VIV007', 7, 'Avenida Andalucía 77, Granada', 1100.00, 95.00,
 'Casa familiar ubicada en zona tranquila.', 1, 'casa'),

('VIV008', 8, 'Calle Jardines 3, Zaragoza', 780.00, 70.00,
 'Apartamento pequeño pero muy funcional.', 0, 'apartamento'),

('VIV009', 9, 'Calle Cervantes 56, Alicante', 1350.00, 120.00,
 'Ático con terraza y plaza de garaje incluida.', 1, 'atico'),

('VIV010', 10, 'Camino Verde 9, Murcia', 1600.00, 145.00,
 'Casa espaciosa con patio interior.', 1, 'casa');
 
 
-- Tabla Inquilino
INSERT INTO inquilino (DNI, nombre, correo_electronico, telefono, tiene_mascota) VALUES
('12345678A', 'Carlos Martínez', 'carlos.martinez@gmail.com', '612345678', 1),
('23456789B', 'Lucía Fernández', 'lucia.fernandez@hotmail.com', '623456789', 0),
('34567890C', 'Miguel Sánchez', 'miguel.sanchez@yahoo.com', '634567890', 1),
('45678901D', 'Ana Gómez', 'ana.gomez@gmail.com', '645678901', 0),
('56789012E', 'David Ruiz', 'david.ruiz@outlook.com', '656789012', 1),
('67890123F', 'Elena Navarro', 'elena.navarro@gmail.com', '667890123', 0),
('78901234G', 'Javier Torres', 'javier.torres@hotmail.com', '678901234', 1),
('89012345H', 'Marta López', 'marta.lopez@yahoo.com', '689012345', 0),
('90123456J', 'Pablo Herrera', 'pablo.herrera@gmail.com', '690123456', 1),
('01234567K', 'Sara Molina', 'sara.molina@outlook.com', '601234567', 0);

-- Tabla Contrato
INSERT INTO contrato (
    id_vivienda,
    id_inquilino,
    fecha_inicio,
    fecha_fin,
    precio,
    estado
)
VALUES
('VIV001', 1, '2025-01-01', '2025-12-31', 850.00, 'activo'),

('VIV002', 2, '2025-02-15', '2026-02-14', 1200.00, 'activo'),

('VIV003', 3, '2024-01-10', '2024-12-31', 700.00, 'vencido'),

('VIV004', 4, '2025-03-01', '2026-03-01', 2500.00, 'pendiente'),

('VIV005', 5, '2025-04-01', '2026-03-31', 950.00, 'activo'),

('VIV006', 6, '2023-06-01', '2024-05-31', 1500.00, 'vencido'),

('VIV007', 7, '2025-05-15', '2026-05-14', 1100.00, 'pendiente'),

('VIV008', 8, '2025-01-20', '2025-11-20', 780.00, 'activo'),

('VIV009', 9, '2024-07-01', '2025-06-30', 1350.00, 'activo'),

('VIV010', 10, '2025-08-01', '2026-07-31', 1600.00, 'pendiente');