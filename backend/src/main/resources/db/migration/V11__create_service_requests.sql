CREATE TABLE service_requests (
                                  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                                  client_id UUID NOT NULL,
                                  professional_id UUID NOT NULL,
                                  service_id UUID NOT NULL,

                                  status_id SMALLINT NOT NULL,

                                  description TEXT,
                                  address VARCHAR(255),
                                  requested_date TIMESTAMPTZ,

                                  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,


                                  CONSTRAINT fk_request_client
                                      FOREIGN KEY (client_id)
                                          REFERENCES users(id),

                                  CONSTRAINT fk_request_professional
                                      FOREIGN KEY (professional_id)
                                          REFERENCES users(id),

                                  CONSTRAINT fk_request_service
                                      FOREIGN KEY (service_id)
                                          REFERENCES services(id),

                                  CONSTRAINT fk_request_status
                                      FOREIGN KEY (status_id)
                                          REFERENCES request_statuses(id)
);