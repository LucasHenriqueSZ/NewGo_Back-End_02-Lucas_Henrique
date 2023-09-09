CREATE TABLE produtos
(
    id          serial PRIMARY KEY,
    hash        uuid           NOT NULL unique,
    nome        varchar(255)   NOT NULL,
    descricao   text,
    ean13       varchar(13)    NOT NULL unique,
    preco       numeric(13, 2) NOT NULL CHECK (preco >= 0),
    quantidade  integer        NOT NULL CHECK (quantidade >= 0),
    estoque_min integer        NOT NULL CHECK (estoque_min >= 0),
    dtcreate    timestamp      NOT NULL,
    dtupdate    timestamp,
    l_ativo     boolean        NOT NULL
);