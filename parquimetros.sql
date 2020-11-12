# Creo la base de datos

CREATE DATABASE parquimetros;

# Selecciono la base de datos

USE parquimetros;

# ----------------------------------------------------------------------------
# ============================================================================
# ----------------------------------------------------------------------------
# Creación de tablas para las Entidades

CREATE TABLE conductores (
    dni INT UNSIGNED NOT NULL, 
    nombre VARCHAR(45) NOT NULL, 
    apellido VARCHAR(45) NOT NULL,
    direccion VARCHAR(45) NOT NULL,
    telefono VARCHAR(45),
    registro INT UNSIGNED NOT NULL,

    CONSTRAINT pk_conductores 
    PRIMARY KEY (dni)

) ENGINE=InnoDB;


CREATE TABLE automoviles (
    patente VARCHAR(6) NOT NULL, 
    marca VARCHAR(45) NOT NULL, 
    modelo VARCHAR(45) NOT NULL,
    color VARCHAR(45) NOT NULL,
    dni INT UNSIGNED NOT NULL,

    CONSTRAINT pk_automoviles 
    PRIMARY KEY (patente),

    CONSTRAINT fk_automoviles_conductores
    FOREIGN KEY (dni) REFERENCES conductores(dni)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE tipos_tarjeta (
    tipo VARCHAR(25) NOT NULL,
    descuento DECIMAL(3,2) UNSIGNED NOT NULL,

    CONSTRAINT chk_descuento CHECK (descuento >= 0 AND descuento <= 1),

    CONSTRAINT pk_tipos_tarjeta
    PRIMARY KEY (tipo)
) ENGINE=InnoDB;


CREATE TABLE tarjetas (
    id_tarjeta INT UNSIGNED NOT NULL AUTO_INCREMENT,
    saldo DECIMAL(5,2) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    patente VARCHAR(6) NOT NULL,

    CONSTRAINT pk_tarjetas
    PRIMARY KEY (id_tarjeta),

    CONSTRAINT fk_tarjeta_Automoviles
    FOREIGN KEY (patente) REFERENCES automoviles(patente)
        ON DELETE RESTRICT ON UPDATE CASCADE,

    CONSTRAINT fk_tarjetas_tipos_tarjeta
    FOREIGN KEY (tipo) REFERENCES tipos_tarjeta(tipo)
        ON DELETE RESTRICT ON UPDATE CASCADE 
) ENGINE=InnoDB;


CREATE TABLE inspectores(
    legajo INT UNSIGNED NOT NULL,
    dni INT UNSIGNED NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    password CHAR(32) NOT NULL,

    CONSTRAINT pk_inspectores
    PRIMARY KEY (legajo)
) ENGINE=InnoDB;


CREATE TABLE ubicaciones(
    calle VARCHAR(45) NOT NULL,
    altura INT UNSIGNED NOT NULL,
    tarifa DECIMAL(5,2) UNSIGNED NOT NULL,

    CONSTRAINT pk_ubicaciones
    PRIMARY KEY (calle,altura)
) ENGINE=InnoDB;


CREATE TABLE parquimetros(
    id_parq INT UNSIGNED NOT NULL AUTO_INCREMENT,
    numero INT UNSIGNED NOT NULL,
    calle VARCHAR(45) NOT NULL,
    altura INT UNSIGNED NOT NULL,

    CONSTRAINT pk_parquimetros
    PRIMARY KEY (id_parq),

    CONSTRAINT fk_parquimetros_ubicaciones
    FOREIGN KEY (calle,altura) REFERENCES ubicaciones(calle,altura)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;


# ----------------------------------------------------------------------------
# ----------------------------------------------------------------------------
# Creación de tablas para las relaciones

CREATE TABLE asociado_con (
    id_asociado_con INT UNSIGNED NOT NULL AUTO_INCREMENT,
    legajo INT UNSIGNED NOT NULL,
    calle  VARCHAR(45) NOT NULL,
    altura INT UNSIGNED NOT NULL,
    dia ENUM ('do','lu','ma','mi','ju','vi','sa') NOT NULL,
    turno ENUM ('m','t') NOT NULL,

    CONSTRAINT pk_asociado_con
    PRIMARY KEY (id_asociado_con),

    CONSTRAINT fk_asociado_con_inspectores
    FOREIGN KEY (legajo) REFERENCES inspectores(legajo)
        ON DELETE RESTRICT ON UPDATE CASCADE,

    CONSTRAINT fk_asociado_con_ubicaciones
    FOREIGN KEY (calle, altura) REFERENCES ubicaciones(calle, altura)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE multa (
    numero INT UNSIGNED NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    patente VARCHAR(6) NOT NULL,
    id_asociado_con INT UNSIGNED NOT NULL, 

    CONSTRAINT pk_multa
    PRIMARY KEY (numero),

    CONSTRAINT fk_multa_automoviles 
    FOREIGN KEY (patente) REFERENCES automoviles(patente) 
        ON DELETE RESTRICT ON UPDATE CASCADE,
  
    CONSTRAINT fk_multa_asociado_con 
    FOREIGN KEY (id_asociado_con) REFERENCES asociado_con(id_asociado_con)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE accede (
    legajo INT UNSIGNED NOT NULL,
    id_parq INT UNSIGNED NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,

    CONSTRAINT pk_accede
    PRIMARY KEY (id_parq, fecha, hora),

    CONSTRAINT fk_accede_inspectores
    FOREIGN KEY (legajo) REFERENCES inspectores(legajo)
        ON DELETE RESTRICT ON UPDATE CASCADE,

    CONSTRAINT fk_accede_parquimetros
    FOREIGN KEY (id_parq) REFERENCES parquimetros(id_parq)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE estacionamientos(
    id_tarjeta INT UNSIGNED NOT NULL,
    id_parq INT UNSIGNED NOT NULL,
    fecha_ent DATE NOT NULL,
    hora_ent TIME NOT NULL,
    fecha_sal DATE,
    hora_sal TIME,

    CONSTRAINT pk_estacionamientos
    PRIMARY KEY (id_parq, fecha_ent, hora_ent),

    CONSTRAINT fk_estacionamientos_tarjeta
    FOREIGN KEY (id_tarjeta) REFERENCES tarjetas(id_tarjeta)
        ON DELETE RESTRICT ON UPDATE CASCADE,

    CONSTRAINT fk_estacionamientos_parquimetros
    FOREIGN KEY (id_parq) REFERENCES parquimetros(id_parq)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;


# ----------------------------------------------------------------------------
# ============================================================================
# ----------------------------------------------------------------------------
# Creación de Vistas

CREATE VIEW estacionados AS 
   SELECT pq.calle, pq.altura, t.patente
   FROM (Tarjetas t NATURAL JOIN Estacionamientos e) NATURAL JOIN Parquimetros pq
   WHERE e.fecha_sal IS NULL AND e.hora_sal IS NULL;

# ----------------------------------------------------------------------------
# ============================================================================
# ----------------------------------------------------------------------------
# Creación de stored procedures
delimiter !

CREATE PROCEDURE conectar(IN id_tarjeta INT, IN id_parq INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SELECT 'SQLEXCEPTION!, transacción abortada' AS resultado;
        ROLLBACK;
    END;

    START TRANSACTION

        IF EXISTS (SELECT * FROM Parquimetros WHERE Parquimetros.id_parq = id_parq) AND (SELECT * from Tarjetas WHERE Tarjetas.id_tarjeta = id_tarjeta) THEN
            IF EXISTS (SELECT * FROM Estacionamientos e WHERE e.id_parq = id_parq AND e.id_tarjeta = id_tarjeta AND e.fecha_sal IS NULL) THEN
                # Se quiere cerrar un estacionamiento #

                DECLARE f_ent DATE;
                DECLARE h_ent TIME; 
                SELECT fecha_ent INTO f_ent FROM Estacionamientos e WHERE e.id_tarjeta = id_tarjeta AND e.id_parq = id_parq;
                SELECT hora_ent INTO h_ent FROM Estacionamientos e WHERE e.id_tarjeta = id_tarjeta AND e.id_parq = id_parq;
                SET tiempo = TIME_TO_SEC(TIMEDIFF(now(), CONCAT(f_ent,' ',h_ent))) / 60;

                UPDATE  (Ubicaciones u NATURAL JOIN Parquimetros p), (tarjetas t NATURAL JOIN tipos_tarjeta tt)
                    SET t.saldo = t.saldo - (tiempo * u.tarifa * (1-tt.descuento))
                    WHERE t.id_tarjeta = id_tarjeta AND p.id_parq = id_parq;

                UPDATE Estacionamientos e SET fecha_sal = CURDATE(), hora_sal = CURTIME() WHERE e.id_tarjeta = id_tarjeta AND e.id_parq = id_parq;

                SELECT 'cierre' AS Operacion, tiempo AS tiempo_transcurrido(min), t.saldo as Saldo_actual FROM Tarjetas t WHERE t.id_tarjeta = id_tarjeta; 
            ELSE
                # Se quiere abrir un estacionamiento #

                DECLARE saldo_disponible DECIMAL(5,2);
                SELECT saldo INTO saldo_disponible FROM Tarjetas t WHERE t.id_tarjeta = id_tarjeta;
                IF (saldo_disponible > 0) THEN
                    INSERT INTO Estacionamientos(id_tarjeta,id_parq,fecha_ent,hora_ent,fecha_sal,hora_sal) VALUES (id_tarjeta, id_parq, CURDATE(), CURTIME());

                    SELECT 'apertura' AS Operacion,  'operacion realizada exitosamente' AS Estado, 130.00 / ((u.tarifa)*(1-tt.descuento)) AS tiempo_disponible
                        FROM (Tarjetas t NATURAL JOIN tipos_tarjeta tt), (Ubicaciones u NATURAL JOIN Parquimetros p)
                        WHERE t.id_tarjeta = id_tarjeta AND p.id_parq = id_parq;
                ELSE
                    SELECT 'apertura' AS Operacion, 'saldo insuficiente' AS Estado;
                END IF;
        ELSE
            SELECT 'error: id_parq o id_tarjeta inexistentes' AS Operacion;
        END IF;

    COMMIT;
END; !


delimiter ; 

# ----------------------------------------------------------------------------
# ============================================================================
# ----------------------------------------------------------------------------
# Creación de usuarios y otorgamiento de privilegios


    CREATE USER 'admin'@'localhost'  IDENTIFIED BY 'admin';

# El usuario admin solamente podrá conectarse desde la computadora que corre el servidor (localhost).

    GRANT ALL PRIVILEGES ON parquimetros.* TO 'admin'@'localhost' WITH GRANT OPTION;

# El usuario 'admin' tiene acceso total a todas las tablas de 
# la B.D. parquimetros y puede crear nuevos usuarios y otorgar privilegios.

#-------

    CREATE USER 'venta'@'%' IDENTIFIED by 'venta';

# El usuario venta podrá conectarse desde cualquiera computadora.

    GRANT SELECT,INSERT ON parquimetros.Tarjetas TO 'venta'@'%';
    GRANT SELECT,INSERT ON parquimetros.tipos_tarjeta TO 'venta'@'%';   
# El usuario venta solamente puede acceder a la tabla tarjeta con permiso para seleccionar e insertar

#-------

    CREATE USER 'inspector'@'%' IDENTIFIED BY 'inspector';

# El usuario inspector podrá conectarse desde cualquier computadora.

    GRANT SELECT ON parquimetros.Inspectores TO 'inspector'@'%';
    GRANT SELECT, INSERT ON parquimetros.Multa TO 'inspector'@'%';
    GRANT SELECT ON parquimetros.Estacionados TO 'inspector'@'%';
    GRANT SELECT, INSERT ON parquimetros.Accede to 'inspector'@'%';
    GRANT SELECT ON parquimetros.Asociado_con to 'inspector'@'%';
    GRANT SELECT ON parquimetros.Parquimetros to 'inspector'@'%';
    GRANT SELECT ON parquimetros.Automoviles to 'inspector'@'%';
    GRANT CREATE TEMPORARY TABLES ON parquimetros.* TO 'inspector'@'%';