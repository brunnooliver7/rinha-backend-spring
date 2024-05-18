CREATE SCHEMA rinha;

CREATE TABLE IF NOT EXISTS rinha.pessoa
(
    id         uuid         not null primary key,
    apelido    varchar(32)  not null unique,
    nome       varchar(100) not null,
    nascimento varchar(10)  not null,
    stack      text         null,
    version    int,
    termo     TEXT GENERATED ALWAYS AS (nome || apelido || stack) STORED
);

CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS trgm_idx ON rinha.pessoa USING gist (termo gist_trgm_ops);
