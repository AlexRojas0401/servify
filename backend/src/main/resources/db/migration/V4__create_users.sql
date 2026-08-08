CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                       role_id SMALLINT NOT NULL,
                       status_id SMALLINT NOT NULL,

                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(150) NOT NULL,

                       email VARCHAR(255) NOT NULL UNIQUE,
                       phone_number VARCHAR(20) NOT NULL UNIQUE,

                       password_hash VARCHAR(255),

                       email_verified BOOLEAN NOT NULL DEFAULT FALSE,
                       phone_verified BOOLEAN NOT NULL DEFAULT FALSE,

                       profile_image_url TEXT,

                       created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       deleted_at TIMESTAMPTZ,

                       CONSTRAINT fk_users_role
                           FOREIGN KEY (role_id)
                               REFERENCES roles(id),

                       CONSTRAINT fk_users_status
                           FOREIGN KEY (status_id)
                               REFERENCES user_statuses(id)
);

CREATE INDEX idx_users_role_id
    ON users(role_id);

CREATE INDEX idx_users_status_id
    ON users(status_id);
