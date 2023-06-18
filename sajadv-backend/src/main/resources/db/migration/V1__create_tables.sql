-- src/main/resources/db/migration/V1__create_tables.sql
CREATE TABLE user (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    dtnasc DATE,
    email VARCHAR(400) NOT NULL,
    ativo BOOLEAN,
    avatar BYTEA
);
