CREATE TABLE IF NOT EXISTS categoria
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL   
);

CREATE TABLE IF NOT EXISTS Curso
(
    id SERIAL PRIMARY KEY,
    nome varchar(50) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    categoria_id INT NOT NULL,
    valor MONEY NOT NULL,
    desconto INT NOT NULL,
    details jsonb NULL,

    CONSTRAINT fk_curso_categoria foreign key (categoria_id)
        REFERENCES categoria(id)
);


CREATE TABLE IF NOT EXISTS TipoUsuario
(
    id int not null,
    nome varchar(20) not null,

    constraint pk_tipo primary key (id)
);

CREATE TABLE IF NOT EXISTS Usuario
(
    Login VARCHAR(50) not null,
    Senha varchar(50) not null,
    TipoUsuarioId int not null,

    constraint pk_usuario primary key (login),
    constraint fk_usuario foreign key (TipoUsuarioId)
        references TipoUsuario(id)
);

CREATE OR REPLACE FUNCTION  verifyFunc(email varchar(50), pass varchar(50))
returns varchar(1) as $$
DECLARE result varchar(1);
BEGIN

    IF EXISTS(select CAST(TipoUsuarioId as varchar) as tipoUsuarioId from usuario where login=email and senha=pass) THEN
        result = (select CAST(TipoUsuarioId as varchar) as tipoUsuarioId from usuario where login=email and senha=pass);
    else
        result = '0';
    end if;
    return result;
END;
$$ LANGUAGE plpgsql;