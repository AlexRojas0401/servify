CREATE TABLE professional_profiles (
                                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                                       user_id UUID NOT NULL UNIQUE,

                                       business_name VARCHAR(150),

                                       description TEXT,

                                       experience_years SMALLINT,

                                       verified BOOLEAN NOT NULL DEFAULT FALSE,

                                       created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       deleted_at TIMESTAMPTZ,

                                       CONSTRAINT fk_professional_profiles_user
                                           FOREIGN KEY (user_id)
                                               REFERENCES users(id)
);