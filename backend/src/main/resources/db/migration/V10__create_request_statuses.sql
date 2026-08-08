CREATE TABLE request_statuses (

                                  id SMALLINT PRIMARY KEY,

                                  code VARCHAR(50) NOT NULL UNIQUE,

                                  name VARCHAR(100) NOT NULL,

                                  description VARCHAR(255),

                                  active BOOLEAN NOT NULL DEFAULT TRUE,

                                  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO request_statuses
(id, code, name, description, active)
VALUES
    (1, 'PENDING', 'Pendiente', 'Waiting for professional response', TRUE),

    (2, 'ACCEPTED', 'Aceptada', 'Professional accepted the request', TRUE),

    (3, 'REJECTED', 'Rechazada', 'Professional rejected the request', TRUE),

    (4, 'CANCELLED', 'Cancelada', 'Cancelled by client', TRUE),

    (5, 'IN_PROGRESS', 'En progreso', 'Professional is performing the service', TRUE),

    (6, 'COMPLETED', 'Completada', 'Service completed successfully', TRUE);