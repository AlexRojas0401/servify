CREATE TABLE user_statuses (
                               id SMALLINT PRIMARY KEY,
                               code VARCHAR(50) NOT NULL UNIQUE,
                               name VARCHAR(100) NOT NULL,
                               description VARCHAR(255),
                               active BOOLEAN NOT NULL DEFAULT TRUE,
                               created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO user_statuses (id, code, name, description, active) VALUES
                                                                    (1, 'PENDING_VERIFICATION', 'Pendiente de verificación', 'User must verify email address', TRUE),
                                                                    (2, 'ACTIVE', 'Activo', 'User can access the platform', TRUE),
                                                                    (3, 'SUSPENDED', 'Suspendido', 'User suspended by an administrator', TRUE),
                                                                    (4, 'DELETED', 'Eliminado', 'User account logically deleted', TRUE);