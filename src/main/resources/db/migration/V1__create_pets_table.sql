CREATE EXTENSION IF NOT EXISTS unaccent;

CREATE OR REPLACE FUNCTION imutavel_unaccent(text)
RETURNS text AS $$
SELECT unaccent('unaccent', $1)
           $$ LANGUAGE sql IMMUTABLE;

CREATE TABLE pets
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome          varchar(120) NOT NULL,
    sobrenome     varchar(120),
    tipo          varchar(20)  NOT NULL,
    sexo          varchar(20)  NOT NULL,
    numero_casa   varchar(20)   DEFAULT 'NAO_INFORMADO',
    cidade        varchar(120)  DEFAULT 'NAO_INFORMADO',
    rua           varchar(120)  DEFAULT 'NAO_INFORMADO',
    idade         NUMERIC(4, 2) DEFAULT 0,
    peso          NUMERIC(5, 2) DEFAULT 0,
    raca          varchar(120)  DEFAULT 'NAO_INFORMADO',
    ativo         BOOLEAN NOT NULL DEFAULT true,
    data_cadastro TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_pets_nome ON pets (LOWER(imutavel_unaccent(nome)));
CREATE INDEX idx_pets_tipo ON pets (tipo);