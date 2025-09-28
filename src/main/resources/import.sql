-- Script de inicialización para PostgreSQL - data.sql
-- Base de datos: insalud

-- Insertar Personas
INSERT INTO persona (nombre, email, estado) VALUES ('Juan Pérez', 'juan.perez@email.com', 'ACTIVO');
INSERT INTO persona (nombre, email, estado) VALUES ('Dr. María García', 'maria.garcia@email.com', 'ACTIVO');
INSERT INTO persona (nombre, email, estado) VALUES ('Admin Carlos', 'admin.carlos@email.com', 'ACTIVO');

-- Insertar Usuarios (contraseña: "12345")
INSERT INTO usuario (usuario, contrasena, persona_id) VALUES ('paciente1', '$2a$10$hTanqtPe2qSouviVZI8Eh.TEgv/41nCv8SlZztBh7BsQDNt6jCEQa', 1);
INSERT INTO usuario (usuario, contrasena, persona_id) VALUES ('medico1', '$2a$10$hTanqtPe2qSouviVZI8Eh.TEgv/41nCv8SlZztBh7BsQDNt6jCEQa', 2);
INSERT INTO usuario (usuario, contrasena, persona_id) VALUES ('admin1', '$2a$10$hTanqtPe2qSouviVZI8Eh.TEgv/41nCv8SlZztBh7BsQDNt6jCEQa', 3);

-- Insertar Paciente
INSERT INTO paciente (rol, estado, persona_id) VALUES ('PACIENTE', 'ACTIVO', 1);

-- Insertar Empleados
INSERT INTO empleado (rol, estado, persona_id) VALUES ('MEDICO', 'ACTIVO', 2);
INSERT INTO empleado (rol, estado, persona_id) VALUES ('ADMIN', 'ACTIVO', 3);

-- Insertar Especialidades
INSERT INTO especialidad (nombre, estado) VALUES ('Medicina General', 'ACTIVO');
INSERT INTO especialidad (nombre, estado) VALUES ('Cardiología', 'ACTIVO');

-- Insertar relación Médico-Especialidad (empleado_id=1 es el médico)
INSERT INTO medico_especialidad (empleado_id, especialidad_id) VALUES (1, 1);
INSERT INTO medico_especialidad (empleado_id, especialidad_id) VALUES (1, 2);

-- Insertar Atenciones (paciente_id=1, empleado_id=1)
INSERT INTO atencion (fecha, motivo, estado, paciente_id, empleado_id) VALUES (DATE '2024-01-15', 'Control médico general', 'COMPLETADA', 1, 1);
INSERT INTO atencion (fecha, motivo, estado, paciente_id, empleado_id) VALUES (DATE '2024-01-25', 'Chequeo cardíaco', 'PENDIENTE', 1, 1);