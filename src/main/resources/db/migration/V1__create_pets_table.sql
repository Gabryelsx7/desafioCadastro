CREATE TABLE petShop
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome        varchar(120) NOT NULL,
    sobrenome   varchar(120),
    tipo        varchar(20)  NOT NULL,
    sexo        varchar(20)  NOT NULL,
    numero_casa varchar(20)      DEFAULT 'NÃO_INFORMADO',
    cidade      varchar(120)     DEFAULT 'NÃO_INFORMADO',
    rua         varchar(120)     DEFAULT 'NÃO_INFORMADO',
    idade       NUMERIC(4, 2)    DEFAULT 0,
    peso        NUMERIC(5, 2)    DEFAULT 0,
    raca     varchar(120)     DEFAULT 'NÃO_INFORMADO',
    ativo  BOOLEAN not null default true,
    data_cadastro TIMESTAMP NOT NULL DEFAULT now()

)
CREATE INDEX idx_petShop_nome ON pets (LOWER(unaccent(nome)));
CREATE INDEX idx_petShop_tipo ON pets (tipo);