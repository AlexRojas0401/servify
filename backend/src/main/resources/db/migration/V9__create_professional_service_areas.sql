CREATE TABLE professional_service_areas (

                                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                                            professional_profile_id UUID NOT NULL,

                                            province VARCHAR(100) NOT NULL,

                                            city VARCHAR(100) NOT NULL,

                                            postal_code VARCHAR(10),

                                            created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                            CONSTRAINT fk_service_area_professional
                                                FOREIGN KEY (professional_profile_id)
                                                    REFERENCES professional_profiles(id)
                                                    ON DELETE CASCADE
);

CREATE INDEX idx_service_area_professional
    ON professional_service_areas(professional_profile_id);

CREATE INDEX idx_service_area_city
    ON professional_service_areas(city);

CREATE INDEX idx_service_area_postal_code
    ON professional_service_areas(postal_code);