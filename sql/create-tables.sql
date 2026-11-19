CREATE TABLE roles (
                       id_rol INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       nombre_rol VARCHAR(40) NOT NULL UNIQUE,
                       descripcion VARCHAR(150),
                       estado VARCHAR(20) NOT NULL DEFAULT 'activo'
);

CREATE TABLE usuarios (
                          id_usuario INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          nombre_usuario VARCHAR(100) NOT NULL,
                          correo VARCHAR(120) NOT NULL UNIQUE,
                          contrasena_hash VARCHAR(100) NOT NULL,
                          telefono VARCHAR(20),
                          fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          estado VARCHAR(20) NOT NULL DEFAULT 'activo',
                          id_rol INTEGER NOT NULL REFERENCES roles (id_rol)
);

INSERT INTO roles (nombre_rol, descripcion) VALUES
                                                ('admin', 'Administra la plataforma'),
                                                ('ciudadano', 'Levanta reportes'),
                                                ('tecnico', 'Atiende reportes');