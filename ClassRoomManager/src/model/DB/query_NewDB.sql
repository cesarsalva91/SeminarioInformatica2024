-- Crear base de datos
CREATE DATABASE IF NOT EXISTS classroommanagerdb;

-- Usar la base de datos
USE classroommanagerdb;

-- Crear la tabla Estudiante
CREATE TABLE Estudiante (
    id_estudiante INT PRIMARY KEY AUTO_INCREMENT,  -- Clave primaria, auto incremental
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    matricula INT NOT NULL,  -- Matrícula del estudiante
    contacto VARCHAR(100)  -- Información de contacto
);

-- Crear la tabla Asistencia
CREATE TABLE Asistencia (
    id_asistencia INT PRIMARY KEY AUTO_INCREMENT,  -- Clave primaria, auto incremental
    fecha DATE NOT NULL,  -- Fecha de la asistencia
    estado VARCHAR(20) NOT NULL,  -- Estado de la asistencia (presente, ausente, etc.)
    justificada BOOLEAN NOT NULL,  -- Indica si la ausencia fue justificada
    id_estudiante INT,  -- Relación con la tabla Estudiante
    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id_estudiante)  -- Clave foránea
);

-- Crear la tabla Materia
CREATE TABLE Materia (
    id_materia INT PRIMARY KEY AUTO_INCREMENT,  -- Clave primaria, auto incremental
    nombre VARCHAR(100) NOT NULL,  -- Nombre de la materia
    descripcion TEXT  -- Descripción de la materia
);

-- Crear la tabla Calificacion
CREATE TABLE Calificacion (
    id_calificacion INT PRIMARY KEY AUTO_INCREMENT,  -- Clave primaria, auto incremental
    nota FLOAT NOT NULL,  -- Calificación del estudiante
    id_estudiante INT,  -- Relación con la tabla Estudiante
    id_materia INT,  -- Relación con la tabla Materia
    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id_estudiante),  -- Clave foránea
    FOREIGN KEY (id_materia) REFERENCES Materia(id_materia)  -- Clave foránea
);

-- Crear la tabla Notificacion
CREATE TABLE Notificacion (
    id_notificacion INT PRIMARY KEY AUTO_INCREMENT,  -- Clave primaria, auto incremental
    mensaje TEXT NOT NULL,  -- Mensaje de la notificación
    fecha_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Fecha de envío
    id_estudiante INT,  -- Relación con la tabla Estudiante
    FOREIGN KEY (id_estudiante) REFERENCES Estudiante(id_estudiante)  -- Clave foránea
);
