CREATE TABLE IF NOT EXISTS Categoria(
	id INT NOT NULL,
	nome VARCHAR(50) NOT NULL,
	
	CONSTRAINT pk_categoria PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Produto
(
	id INT NOT NULL,
	nome VARCHAR(100) NOT NULL,
	marca VARCHAR(50) NOT NULL,
	categoria_id INT NOT NULL,
	valor MONEY NOT NULL,
	
	CONSTRAINT pk_produto PRIMARY KEY(id),
	CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES Categoria(id) 
);

CREATE TABLE IF NOT EXISTS Oferta
(
	id INT NOT NULL,
	produto_id INT NOT NULL,
	vl_produto MONEY NOT NULL,
	dt_oferta DATE NOT NULL,
	status INT NOT NULL,
	
	CONSTRAINT pk_oferta PRIMARY KEY(id),
	CONSTRAINT fk_oferta_produto FOREIGN KEY (produto_id) REFERENCES Produto(id)	
)
