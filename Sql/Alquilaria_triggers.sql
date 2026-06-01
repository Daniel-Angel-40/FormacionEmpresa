-- Triggers

USE alquilaria_bd;

DROP TRIGGER IF EXISTS tr_propietario_before_insert;
DELIMITER //
CREATE TRIGGER tr_propietario_before_insert BEFORE INSERT
ON propietario
FOR EACH ROW
BEGIN
	
    SET NEW.DNI = UPPER(NEW.DNI);
    
END //
DELIMITER ; 



DROP TRIGGER IF EXISTS tr_inquilino_before_insert;
DELIMITER //
CREATE TRIGGER tr_inquilino_before_insert BEFORE INSERT
ON inquilino
FOR EACH ROW
BEGIN
	
    SET NEW.DNI = UPPER(NEW.DNI);
    
END //
DELIMITER ; 

DROP TRIGGER IF EXISTS tr_contrato_after_insert;
DELIMITER //
CREATE TRIGGER tr_propietario_after_insert BEFORE INSERT
ON propietario
FOR EACH ROW
BEGIN
	
    SET NEW.DNI = UPPER(NEW.DNI);
    
END //
DELIMITER ; 


DROP TRIGGER IF EXISTS tr_contrato_after_insert;
DELIMITER //
CREATE TRIGGER tr_contrato_after_insert AFTER INSERT
ON contrato
FOR EACH ROW
BEGIN
	
    INSERT INTO contrato_history (id_contrato, id_vivienda, id_inquilino, fecha_inicio, fecha_fin, precio, estado, log_cambio)
    VALUES (NEW.id, NEW.id_vivienda, NEW.id_inquilino, NEW.fecha_inicio, NEW.fecha_fin, NEW.precio, NEW.estado, 'INSERT');

END //
DELIMITER ;


DROP TRIGGER IF EXISTS tr_contrato_after_update;
DELIMITER //
CREATE TRIGGER tr_contrato_after_update AFTER UPDATE
ON contrato
FOR EACH ROW
BEGIN
	
    INSERT INTO contrato_history (id_contrato, id_vivienda, id_inquilino, fecha_inicio, fecha_fin, precio, estado, log_cambio)
    VALUES (NEW.id, NEW.id_vivienda, NEW.id_inquilino, NEW.fecha_inicio, NEW.fecha_fin, NEW.precio, NEW.estado, 'UPDATE');

END //
DELIMITER ;



DROP TRIGGER IF EXISTS tr_contrato_after_delete;
DELIMITER //
CREATE TRIGGER tr_contrato_after_delete AFTER DELETE
ON contrato
FOR EACH ROW
BEGIN
	
    INSERT INTO contrato_history (id_contrato, id_vivienda, id_inquilino, fecha_inicio, fecha_fin, precio, estado, log_cambio)
    VALUES (OLD.id, OLD.id_vivienda, OLD.id_inquilino, OLD.fecha_inicio, OLD.fecha_fin, OLD.precio, OLD.estado, 'DELETE');

END //
DELIMITER ;