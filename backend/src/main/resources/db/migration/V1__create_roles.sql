CREATE TABLE roles (
                       id SMALLINT PRIMARY KEY,
                       code VARCHAR(50) NOT NULL UNIQUE,
                       name VARCHAR(100) NOT NULL,
                       description VARCHAR(255),
                       active BOOLEAN NOT NULL DEFAULT TRUE,
                       created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO roles (id, code, name, description, active) VALUES
                                                            (1, 'CLIENT', 'Cliente', 'Customer who hires professionals', TRUE),
                                                            (2, 'PROFESSIONAL', 'Profesional', 'Professional offering services', TRUE),
                                                            (3, 'ADMIN', 'Administrador', 'Platform administrator', TRUE);