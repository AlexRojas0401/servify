CREATE TABLE categories (
                            id SMALLSERIAL PRIMARY KEY,

                            name VARCHAR(100) NOT NULL UNIQUE,

                            description TEXT,

                            active BOOLEAN NOT NULL DEFAULT TRUE,

                            created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_categories_active
    ON categories(active);