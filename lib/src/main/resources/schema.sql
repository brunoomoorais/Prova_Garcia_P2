CREATE TABLE IF NOT EXISTS categoria
(
    id INT not null,
    nome VARCHAR(50) NOT NULL,

    CONSTRAINT pk_categoria PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Curso
(
    id INT NOT NULL,
    nome varchar(50) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    categoria_id INT NOT NULL,
    valor MONEY NOT NULL,
    desconto INT NOT NULL,
    details jsonb NULL,

    CONSTRAINT pk_curso PRIMARY KEY (id),
    CONSTRAINT fk_curso_categoria foreign key (categoria_id)
        REFERENCES categoria(id)
)