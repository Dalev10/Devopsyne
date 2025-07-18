DROP TABLE IF EXISTS solicitud_material;
DROP TABLE IF EXISTS solicitud;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS tipo_material;
DROP TABLE IF EXISTS solicitante;

/* Autor: Diego Alejandro Vergara Ruiz */

/* 1. Solicitante */
CREATE TABLE solicitante (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(80)  NOT NULL,
    identificacion VARCHAR(20)  NOT NULL UNIQUE,
    email          VARCHAR(120) NOT NULL UNIQUE,
    telefono       VARCHAR(25),
    direccion      VARCHAR(150),
    pass_hash      CHAR(64)     NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

/* 2. TipoMaterial */
CREATE TABLE tipo_material (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(40) NOT NULL UNIQUE,
    descripcion VARCHAR(100)
);

/* 3. Material */
CREATE TABLE material (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_material_id BIGINT NOT NULL,
    peso             DECIMAL(8,2) NOT NULL CHECK (peso >= 0),
    ancho            DECIMAL(8,2) CHECK (ancho >= 0),
    largo            DECIMAL(8,2) CHECK (largo >= 0),
    alto             DECIMAL(8,2) CHECK (alto >= 0),
    FOREIGN KEY (tipo_material_id) REFERENCES tipo_material(id)
);

/* 4. Solicitud */
CREATE TABLE solicitud (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    solicitante_id BIGINT NOT NULL,
    fecha          DATE   NOT NULL,
    estado         ENUM('CREADA', 'EN_PROCESO', 'FINALIZADA', 'CANCELADA') NOT NULL,
    FOREIGN KEY (solicitante_id) REFERENCES solicitante(id)
);

/* 5. Solicitud_Material */
CREATE TABLE solicitud_material (
    solicitud_id BIGINT NOT NULL,
    material_id  BIGINT NOT NULL,
    cantidad     INT    NOT NULL CHECK (cantidad >= 0),
    PRIMARY KEY (solicitud_id, material_id),
    FOREIGN KEY (solicitud_id) REFERENCES solicitud(id) ON DELETE CASCADE,
    FOREIGN KEY (material_id)  REFERENCES material(id)
);

/* Índices */
CREATE INDEX idx_material_tipo    ON material(tipo_material_id);
CREATE INDEX idx_sol_estado       ON solicitud(estado);
CREATE INDEX idx_sol_solicitante  ON solicitud(solicitante_id);
CREATE INDEX idx_detalle_material ON solicitud_material(material_id);

