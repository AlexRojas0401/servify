CREATE TABLE services (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                          category_id SMALLINT NOT NULL,

                          name VARCHAR(150) NOT NULL,

                          description TEXT,

                          active BOOLEAN NOT NULL DEFAULT TRUE,

                          created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT fk_services_category
                              FOREIGN KEY (category_id)
                                  REFERENCES categories(id),

                          CONSTRAINT uq_services_category_name
                              UNIQUE (category_id, name)
);

CREATE INDEX idx_services_category
    ON services(category_id);

CREATE INDEX idx_services_active
    ON services(active);