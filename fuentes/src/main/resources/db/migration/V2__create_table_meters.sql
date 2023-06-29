CREATE TABLE meters (
  id SERIAL PRIMARY KEY,
  evol_id VARCHAR(255) UNIQUE,
  identifier VARCHAR(255) NOT NULL,
  physical_address VARCHAR(255) NOT NULL,
  installation_number VARCHAR(20) NOT NULL,
  client_id BIGINT REFERENCES clients(id)
);