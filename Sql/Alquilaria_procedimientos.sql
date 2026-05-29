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
    OUT viviendas INT,
    OUT contratos INT)
BEGIN

	DECLARE codigo VARCHAR(10);

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SET err = 1;
        SET viviendas = 0;
        SET contratos = 0;
        ROLLBACK;
    END;

	START TRANSACTION;
    
    SELECT id INTO codigo FROM vivienda WHERE id_propietario = p_id;
    
    DELETE FROM contrato WHERE codigo = id_vivienda;
    
    SET contratos = ROW_COUNT();
    
    DELETE FROM vivienda WHERE id_propietario = p_id;
    
    SET viviendas = ROW_COUNT();
    
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


USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_get_vivienda;
DELIMITER //
CREATE PROCEDURE sp_get_vivienda(
	IN p_id VARCHAR(10))
BEGIN
	SELECT * FROM vivienda WHERE id = p_id;
END //
DELIMITER ;



USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_del_vivienda;
DELIMITER //
CREATE PROCEDURE sp_del_vivienda(
	IN p_id VARCHAR(10),
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
    
    DELETE FROM contrato WHERE id_vivienda = p_id;
    
    SET filas = ROW_COUNT();
    
    DELETE FROM vivienda WHERE id = p_id;
    
	IF ROW_COUNT() = 0 THEN
		SET err = 1;
        SET filas = 0;
        ROLLBACK;
    ELSE
        SET err = 0;
        COMMIT;
	END IF;
END //
DELIMITER ;


USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_upd_vivienda;
DELIMITER //
CREATE PROCEDURE sp_upd_vivienda(
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
    
    START TRANSACTION;
    
    UPDATE vivienda SET id_propietario = p_id_propietario, direccion = p_direccion, alquiler_mensual = p_alquiler_mensual, 
    superficie = p_superficie, descripcion = p_descripcion, permite_mascota = p_permite_mascota, tipo = p_tipo WHERE id = p_id;
    
    
    SET err = 0;
    COMMIT;
END //
DELIMITER ;






-- Procedimientos para Inquilino --------------------------------------------------------------------------------------------------------------------------------------------------

USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_ins_inquilino;
DELIMITER //
CREATE PROCEDURE sp_ins_inquilino(
	IN p_DNI VARCHAR(11),
    IN p_nombre VARCHAR(150),
    IN p_correo_electronico VARCHAR(200),
    IN p_telefono VARCHAR(11),
    IN p_tiene_mascota BOOL,
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
    
    INSERT INTO inquilino (DNI, nombre, correo_electronico, telefono, tiene_mascota) 
    VALUES (p_DNI, p_nombre, p_correo_electronico, p_telefono, p_tiene_mascota);
    
    SET id = LAST_INSERT_ID();
    
    SET err = 0;
    COMMIT;
END //
DELIMITER ;



USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_get_inquilino;
DELIMITER //
CREATE PROCEDURE sp_get_inquilino(
	IN p_id INT)
BEGIN
	SELECT * FROM inquilino WHERE id = p_id;
END //
DELIMITER ;



USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_del_inquilino;
DELIMITER //
CREATE PROCEDURE sp_del_inquilino(
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
    
    DELETE FROM contrato WHERE id_inquilino = p_id;
    
    SET filas = ROW_COUNT();
    
    DELETE FROM inquilino WHERE id = p_id;
    
    IF ROW_COUNT() = 0 THEN
		SET err = 1;
		SET filas = 0;
		ROLLBACK;
    ELSE
		SET err = 0;
		COMMIT;
    END IF;
END //
DELIMITER ;



USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_upd_inquilino;
DELIMITER //
CREATE PROCEDURE sp_upd_inquilino(
	IN p_id INT,
    IN p_DNI VARCHAR(11),
    IN p_nombre VARCHAR(150),
    IN p_correo VARCHAR(200),
    IN p_telefono VARCHAR(11),
    IN p_mascota BOOL,
    OUT err INT)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SET err = 1;
        ROLLBACK;
    END;
    
    START TRANSACTION;
	
    UPDATE inquilino SET DNI = p_DNI, nombre = p_nombre, correo_electronico = p_correo, telefono = p_telefono, tiene_mascota = p_mascota WHERE id = p_id;
    
    IF ROW_COUNT() = 0 THEN
		SET err = 1;
        ROLLBACK;
	ELSE
		SET err = 0;
		COMMIT;
    END IF;
END //
DELIMITER ;



-- Procedimientos para Contrato --------------------------------------------------------------------------------------------------------------------------------------------------

USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_ins_contrato;
DELIMITER //
CREATE PROCEDURE sp_ins_contrato(
	IN p_id_vivienda VARCHAR(10),
    IN p_id_inquilino INT,
    IN p_fecha_inicio DATE,
    IN p_fecha_fin DATE,
    IN p_precio DECIMAL(10,2),
    OUT err INT,
    OUT id INT)
BEGIN
    
    DECLARE EXIT HANDLER FOR 3819
    BEGIN
		SET err = 2;
        SET id = -1;
        ROLLBACK;
    END;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SET err = 1;
        SET id = -1;
        ROLLBACK;
    END;
    
    START TRANSACTION;
    
    INSERT INTO contrato (id_vivienda, id_inquilino, fecha_inicio, fecha_fin, precio, estado) 
    VALUES (p_id_vivienda, p_id_inquilino, p_fecha_inicio, p_fecha_fin, p_precio, DEFAULT);
    
    SET id = LAST_INSERT_ID();
    
    SET err = 0;
    COMMIT;
END //
DELIMITER ;



USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_get_contrato;
DELIMITER //
CREATE PROCEDURE sp_get_contrato(
	IN p_id INT)
BEGIN
	SELECT * FROM contrato WHERE id = p_id;
END //
DELIMITER ;



USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_del_contrato;
DELIMITER //
CREATE PROCEDURE sp_del_contrato(
	IN p_id INT,
    OUT err INT)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SET err = 1;
        ROLLBACK;
    END;

	START TRANSACTION;

	DELETE FROM contrato WHERE id = p_id;
    
    IF ROW_COUNT() = 0 THEN
		SET err = 1;
        ROLLBACK;
	ELSE
		SET err = 0;
		COMMIT;
	END IF;
END //
DELIMITER ;



USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_upd_contrato;
DELIMITER //
CREATE PROCEDURE sp_upd_contrato(
	IN p_id INT,
    IN p_inicio DATE,
    IN p_fin DATE,
    IN p_precio DECIMAL(10,2),
    IN p_estado VARCHAR(10),
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

	START TRANSACTION;
	
    UPDATE contrato SET fecha_inicio = p_inicio, fecha_fin = p_fin, precio = p_precio, estado = p_estado WHERE id = p_id;
    
    IF ROW_COUNT() = 0 THEN
		SET err = 1;
        ROLLBACK;
	ELSE
		SET err = 0;
        COMMIT;
    END IF;
END //
DELIMITER ;


USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_actualizar_estado_contrato;
DELIMITER //
CREATE PROCEDURE sp_actualizar_estado_contrato(
	IN p_id INT,
    IN p_num INT,
    OUT err INT)
BEGIN
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SET err = 1;
        ROLLBACK;
    END;
    
    START TRANSACTION;

	CASE p_num
		WHEN 1 THEN UPDATE contrato SET estado = 'pendiente' WHERE id = p_id;
        WHEN 2 THEN UPDATE contrato SET estado = 'activo' WHERE id = p_id;
        WHEN 3 THEN UPDATE contrato SET estado = 'vencido' WHERE id = p_id;
    END CASE;
    
    IF ROW_COUNT() = 0 THEN
		SET err = 1;
        ROLLBACK;
	ELSE
		SET err = 0;
		COMMIT;
	END IF;
END //
DELIMITER ;





-- Procedimientos con Consultas Avanzadas ----------------------------------------------------------------------------------------------------------------------------------------


USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_get_historico_inquilino;
DELIMITER //
CREATE PROCEDURE sp_get_historico_inquilino(
	IN p_id INT)
BEGIN
	SELECT c.id, c.id_vivienda, c.id_inquilino, c.fecha_inicio, c.fecha_fin, c.precio, c.estado
	FROM contrato c
	JOIN vivienda v ON c.id_vivienda = v.id
    JOIN inquilino i ON c.id_inquilino = i.id
	WHERE c.id_inquilino = p_id
	ORDER BY c.fecha_inicio DESC;
END //
DELIMITER ;



USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_get_viviendas_activas;
DELIMITER //
CREATE PROCEDURE sp_get_viviendas_activas(
	IN p_id INT)
BEGIN
	SELECT v.id_propietario, v.id, v.direccion, v.alquiler_mensual, v.superficie, v.descripcion, v.permite_mascota, v.tipo FROM vivienda v
    JOIN propietario p ON v.id_propietario = p.id JOIN contrato c ON v.id = c.id_vivienda WHERE p.id = p_id AND c.estado = 'activo'; 
END //
DELIMITER ;



USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_get_viviendas_libres;
DELIMITER //
CREATE PROCEDURE sp_get_viviendas_libres()
BEGIN
	SELECT v.id, v.direccion, v.alquiler_mensual, v.superficie, v.tipo, v.permite_mascota, v.id_propietario, v.descripcion
	FROM vivienda v
	LEFT JOIN contrato c ON v.id = c.id_vivienda AND c.estado = 'activo'
	WHERE c.id IS NULL;
END //
DELIMITER ;




-- Procedimiento para la actualizacion de contratos ---------------------------------------------------------------------------------------------------------------------------

USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_actualizacion_automatica;
DELIMITER //
CREATE PROCEDURE sp_actualizacion_automatica()
BEGIN

	UPDATE contrato SET estado = 'vencido' WHERE fecha_fin < CURDATE() AND estado != 'vencido';

END //
DELIMITER ;



-- Procedimientos para la exportacion en json de las consultas avanzadas -----------------------------------------------------------------------------------------------------------

USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_get_viviendas_libres_JSON;
DELIMITER //
CREATE PROCEDURE sp_get_viviendas_libres_JSON()
BEGIN

	SET @v_json = json_object();

	SELECT JSON_ARRAYAGG(
    JSON_OBJECT(
        'id', v.id,
        'direccion', v.direccion,
        'alquiler_mensual', v.alquiler_mensual,
        'superficie', v.superficie,
        'tipo', v.tipo,
        'permite_mascota', v.permite_mascota
    )
	) AS resultado INTO @v_json
	FROM vivienda v
	LEFT JOIN contrato c ON v.id = c.id_vivienda AND c.estado = 'activo'
	WHERE c.id IS NULL;

	IF @v_json IS NULL THEN
		SELECT JSON_OBJECT('error', 'sin resultados') AS resultado;
	ELSE
		SELECT @v_json AS resultado;
    END IF;

END //
DELIMITER ;




USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_get_viviendas_activas_propietario_JSON;
DELIMITER //
CREATE PROCEDURE sp_get_viviendas_activas_propietario_JSON(
	IN p_id INT)
BEGIN
	
    SET @v_json = json_object();
    
    SELECT JSON_ARRAYAGG(
    JSON_OBJECT(
    'idVivienda', v.id, 
    'idPropietario', v.id_propietario, 
    'direccion', v.direccion, 
    'alquilerMensual',v.alquiler_mensual, 
    'superficie', v.superficie, 
    'descripcion', v.descripcion, 
    'permiteMascota', v.permite_mascota, 
    'tipo', v.tipo
		)
    ) AS resultado INTO @v_json
    FROM vivienda v
    JOIN propietario p ON v.id_propietario = p.id JOIN contrato c ON v.id = c.id_vivienda WHERE p.id = p_id AND c.estado = 'activo'; 
    
    IF @v_json IS NULL THEN
		SELECT JSON_OBJECT('error', 'sin resultados') AS resultado;
	ELSE
		SELECT @v_json AS resultado;
    END IF;
    
END //
DELIMITER ;


USE alquilaria_bd;
DROP PROCEDURE IF EXISTS sp_get_historico_inquilino_JSON;
DELIMITER //
CREATE PROCEDURE sp_get_historico_inquilino_JSON(
	IN p_id INT)
BEGIN

	SET @v_json = json_object();

	SELECT JSON_ARRAYAGG(
    JSON_OBJECT(
		'idContrato', c.id, 
        'idVivienda', c.id_vivienda, 
        'idInquilino', c.id_inquilino, 
        'fechaInicio', c.fecha_inicio, 
        'fechaFin', c.fecha_fin, 
        'precio', c.precio, 
        'estado', c.estado 
		)
    ) AS resultado INTO @v_json
    FROM contrato c
	JOIN vivienda v ON c.id_vivienda = v.id
    JOIN inquilino i ON c.id_inquilino = i.id
	WHERE c.id_inquilino = p_id
	ORDER BY c.fecha_inicio DESC;

	IF @v_json IS NULL THEN
		SELECT JSON_OBJECT('error', 'sin resultados') AS resultado;
	ELSE
		SELECT @v_json AS resultado;
    END IF;

END //
DELIMITER ;

