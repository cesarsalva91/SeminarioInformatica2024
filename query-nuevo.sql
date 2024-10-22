USE classroommanagerdb;
-- Crear tabla Estudiante
CREATE TABLE Estudiante (
    id_estudiante INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    matricula VARCHAR(50) NOT NULL,
    contacto VARCHAR(100)
);

-- Crear tabla Asistencia
CREATE TABLE Asistencia (
    id_asistencia INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    justificada BOOLEAN NOT NULL,
    id_estudiante INT,
    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id_estudiante) ON DELETE CASCADE
);

-- Crear tabla Calificacion
CREATE TABLE Calificacion (
    id_calificacion INT PRIMARY KEY AUTO_INCREMENT,
    materia VARCHAR(100) NOT NULL,
    nota FLOAT NOT NULL,
    id_estudiante INT,
    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id_estudiante) ON DELETE CASCADE
);

-- Crear tabla Notificacion
CREATE TABLE Notificacion (
    id_notificacion INT PRIMARY KEY AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    fecha_envio DATE NOT NULL,
    id_estudiante INT,
    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id_estudiante) ON DELETE CASCADE
);

-- Insertar datos en la tabla Estudiante
INSERT INTO Estudiante (nombre, apellido, matricula, contacto)
VALUES 
('Juan', 'Pérez', 'M001', 'juan.perez@example.com'),
('María', 'Gómez', 'M002', 'maria.gomez@example.com'),
('Pedro', 'Lopez', 'M003', 'pedro.lopez@example.com');

-- Insertar datos en la tabla Asistencia
INSERT INTO Asistencia (fecha, estado, justificada, id_estudiante)
VALUES 
('2024-10-01', 'Presente', FALSE, 1),
('2024-10-01', 'Ausente', TRUE, 2),
('2024-10-01', 'Ausente', FALSE, 3),
('2024-10-02', 'Presente', FALSE, 1),
('2024-10-02', 'Presente', FALSE, 2),
('2024-10-02', 'Presente', FALSE, 3);

-- Insertar datos en la tabla Calificacion
INSERT INTO Calificacion (materia, nota, id_estudiante)
VALUES 
('Matemáticas', 8.5, 1),
('Ciencias', 7.0, 1),
('Matemáticas', 6.0, 2),
('Ciencias', 9.0, 2),
('Matemáticas', 9.5, 3),
('Ciencias', 8.5, 3);

-- Insertar datos en la tabla Notificacion
INSERT INTO Notificacion (mensaje, fecha_envio, id_estudiante)
VALUES 
('El alumno Juan Pérez tiene una ausencia injustificada el 01-10-2024', '2024-10-01', 1),
('El alumno María Gómez tiene una ausencia justificada el 01-10-2024', '2024-10-01', 2),
('El alumno Pedro Lopez tiene una ausencia injustificada el 01-10-2024', '2024-10-01', 3);

-- Consultar todos los estudiantes registrados

SELECT * FROM Estudiante;

-- Consultar las asistencias de un estudiante por nombre (ejemplo: Juan Pérez):

SELECT E.nombre, E.apellido, A.fecha, A.estado, A.justificada
FROM Asistencia A
JOIN Estudiante E ON A.id_estudiante = E.id_estudiante
WHERE E.nombre = 'Juan' AND E.apellido = 'Pérez';

-- Consultar las calificaciones de un estudiante específico (ejemplo: María Gómez):

SELECT E.nombre, E.apellido, C.materia, C.nota
FROM Calificacion C
JOIN Estudiante E ON C.id_estudiante = E.id_estudiante
WHERE E.nombre = 'María' AND E.apellido = 'Gómez';

-- Consultar las notificaciones enviadas a los tutores de los estudiantes:

SELECT N.mensaje, N.fecha_envio, E.nombre, E.apellido
FROM Notificacion N
JOIN Estudiante E ON N.id_estudiante = E.id_estudiante;

-- Eliminar un estudiante específico (ejemplo: Juan Pérez):

DELETE FROM Estudiante
WHERE nombre = 'Juan' AND apellido = 'Pérez';




