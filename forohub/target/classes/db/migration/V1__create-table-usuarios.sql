DROP TABLE if exists usuarios;
CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL unique,
    clave VARCHAR(255) NOT NULL,
    perfil VARCHAR(255) NOT NULL
);
