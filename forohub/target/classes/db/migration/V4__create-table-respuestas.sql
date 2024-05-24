DROP TABLE IF EXISTS respuestas;

CREATE TABLE respuestas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    topico_id BIGINT,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT,
    solucion BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (topico_id) REFERENCES topicos(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
