-- Procedimientos para hacer CRUD
USE alquilaria_bd;


-- Procedimientos para Propietario -------------------------------------------------------------------------------------------------------------------------------------
DROP PROCEDURE IF EXISTS sp_ins_propietario;
DELIMITER //
CREATE PROCEDURE sp_ins_propietario(
	IN p_DNI VARCHAR(11),
    IN p_nombre VARCHAR(150),
    IN p_correo VARCHAR(200),
    IN p_telefono VARCHAR(11),
    OUT err INT,
    OUT id INT)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SET err = 1;
        SET id = -1;
        ROLLBACK;
    END;

	START TRANSACTION;
    
	INSERT INTO propietario (DNI, nombre, correo_electronico, telefono) VALUES(p_DNI, p_nombre, p_correo, p_telefono);

	SET id = LAST_INSERT_ID();

	COMMIT;
    SET err = 0;
END //
DELIMITER ;


USE alquilaria_bd;

DROP PROCEDURE IF EXISTS sp_get_propietario;
DELIMITER //
CREATE PROCEDURE sp_get_propietario(
	IN p_id INT)
BEGIN
	SELECT * FROM propietario WHERE id = p_id;
END //
DELIMITER ;


USE alquilaria_bd;

DROP PROCEDURE IF EXISTS sp_del_propietario;
DELIMITER //
CREATE PROCEDURE sp_del_propietario(
	IN p_id INT,
    OUT err INT,
    OUT filas INT)
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SET err = 1;
        SET filas = 0;
        ROLLBACK;
    END;

	START TRANSACTION;
    
    DELETE FROM vivienda WHERE id_propietario = p_id;
    
    SET filas = ROW_COUNT();
    
    DELETE FROM propietario WHERE id = p_id;
    
    COMMIT;
    SET err = 0;
END //
DELIMITER ;


DROP PROCEDURE IF EXISTS sp_upd_propietario;
DELIMITER //
CREATE PROCEDURE sp_upd_propietario(
	IN p_id INT,
    IN p_DNI VARCHAR(11),
    IN p_nombre VARCHAR(150),
    IN p_correo VARCHAR(200),
    IN p_telefono VARCHAR(11),
    OUT err INT)
BEGIN

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SET err = 1;
        ROLLBACK;
    END;
    
    START TRANSACTION;
	
    UPDATE propietario SET DNI = p_DNI, nombre = p_nombre, correo_electronico = p_correo, telefono = p_telefono WHERE id = p_id;
    
    IF ROW_COUNT() = 0 THEN
		SET err = 1;
        ROLLBACK;
	ELSE
		SET err = 0;
		COMMIT;
    END IF;
END//
DELIMITER ;



-- Procedimientos para la tabla Vivienda -------------------------------------------------------------------------------------------------------------------------------------------

USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_ins_vivienda;
DELIMITER //
CREATE PROCEDURE sp_ins_vivienda(
	IN p_id VARCHAR(10),
    IN p_id_propietario INT,
    IN p_direccion VARCHAR(100),
    IN p_alquiler_mensual DECIMAL(10,2),
    IN p_superficie DECIMAL(10,2),
    IN p_descripcion VARCHAR(500),
    IN p_permite_mascota BOOL,
    IN p_tipo VARCHAR(15),
    OUT err INT)
BEGIN
	
    DECLARE EXIT HANDLER FOR 3819
    BEGIN
		SET err = 2;
        ROLLBACK;
    END;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SET err = 1;
        ROLLBACK;
    END;

	INSERT INTO vivienda (id, id_propietario, direccion, alquiler_mensual, superficie, descripcion, permite_mascota, tipo) 
    VALUES (p_id, p_id_propietario, p_direccion, p_alquiler_mensual, p_superficie, p_descripcion, p_permite_mascota, p_tipo);

	SET err = 0;
	COMMIT;
END //
DELIMITER ;



