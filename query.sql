USE classroommanagerdb;
CREATE TABLE estudiantes (
    id_estudiante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    matricula VARCHAR(10) UNIQUE NOT NULL,
    contacto VARCHAR(100)
);


CREATE TABLE asistencia (
    id_asistencia INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT,
    fecha DATE NOT NULL,
    estado ENUM('Presente', 'Ausente') NOT NULL,
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE
);

CREATE TABLE calificaciones (
    id_calificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT,
    materia VARCHAR(50) NOT NULL,
    nota DECIMAL(5,2) CHECK (nota >= 0 AND nota <= 10),  -- Suponiendo un rango de 0 a 10
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE
);

CREATE TABLE notificaciones (
    id_notificacion INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT,
    mensaje VARCHAR(255) NOT NULL,
    fecha_envio DATE NOT NULL,
    FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante) ON DELETE CASCADE
);




######Insercion, Consulta y Borrado de Registros


INSERT INTO estudiantes (nombre, apellido, matricula, contacto)
VALUES 
('Juan', 'Pérez', 'A001', 'juan.perez@example.com'),
('María', 'Gómez', 'A002', 'maria.gomez@example.com');


INSERT INTO asistencia (id_estudiante, fecha, estado)
VALUES
(1, '2024-10-06', 'Presente'),
(2, '2024-10-06', 'Ausente');


INSERT INTO calificaciones (id_estudiante, materia, nota)
VALUES
(1, 'Matemáticas', 8.5),
(2, 'Historia', 7.0);


INSERT INTO notificaciones (id_estudiante, mensaje, fecha_envio)
VALUES
(1, 'Notificación de asistencia: Presente', '2024-10-06'),
(2, 'Notificación de asistencia: Ausente', '2024-10-06');


DELETE FROM asistencia WHERE id_asistencia = 1;

SELECT * FROM asistencia;

