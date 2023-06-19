-- src/main/resources/db/migration/V1__create_tables.sql
CREATE TABLE user_sajadv (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    birthDate DATE,
    email VARCHAR(400) NOT NULL,
    active BOOLEAN,
    avatar BYTEA
);
