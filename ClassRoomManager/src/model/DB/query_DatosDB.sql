-- Insertar datos de prueba en la tabla Estudiante
INSERT INTO Estudiante (nombre, apellido, matricula, contacto)
VALUES
('Juan', 'Pérez', 123456, 'juan.perez@email.com'),
('Ana', 'Gómez', 123457, 'ana.gomez@email.com'),
('Carlos', 'Díaz', 123458, 'carlos.diaz@email.com'),
('Laura', 'Martínez', 123459, 'laura.martinez@email.com');

-- Insertar datos de prueba en la tabla Materia
INSERT INTO Materia (nombre, descripcion)
VALUES
('Matemáticas', 'Curso de álgebra y cálculo'),
('Lengua', 'Curso de literatura y gramática'),
('Ciencias', 'Curso de biología y física'),
('Historia', 'Curso de historia universal');

-- Insertar datos de prueba en la tabla Asistencia
INSERT INTO Asistencia (fecha, estado, justificada, id_estudiante)
VALUES
('2024-11-10', 'Presente', TRUE, 1),
('2024-11-10', 'Ausente', FALSE, 2),
('2024-11-11', 'Presente', TRUE, 3),
('2024-11-11', 'Ausente', TRUE, 4);

-- Insertar datos de prueba en la tabla Calificacion
INSERT INTO Calificacion (nota, id_estudiante, id_materia)
VALUES
(8.5, 1, 1),  -- Juan Pérez, Matemáticas
(7.0, 2, 2),  -- Ana Gómez, Lengua
(9.2, 3, 3),  -- Carlos Díaz, Ciencias
(6.8, 4, 4);  -- Laura Martínez, Historia

-- Insertar datos de prueba en la tabla Notificacion
INSERT INTO Notificacion (mensaje, id_estudiante)
VALUES
('Reunión de padres programada para el 20 de noviembre', 1),
('Cambio de horario en el curso de Lengua para el miércoles', 2),
('El examen final de Ciencias será el 25 de noviembre', 3),
('Los alumnos deben entregar el proyecto final de Historia antes del 30 de noviembre', 4);
