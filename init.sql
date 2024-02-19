CREATE UNLOGGED TABLE clientes (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	limite INTEGER NOT NULL,
    saldo INTEGER NOT NULL
);

CREATE UNLOGGED TABLE transacoes (
	id SERIAL PRIMARY KEY,
	cliente_id INTEGER NOT NULL,
	valor INTEGER NOT NULL,
	tipo int NOT NULL,
	descricao VARCHAR(10) NOT NULL,
	realizada_em TIMESTAMP NOT NULL DEFAULT NOW(),
	CONSTRAINT fk_clientes_transacoes_id
		FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);


DO $$
BEGIN
INSERT INTO clientes (nome, limite, saldo)
VALUES
    ('diablo', 1000 * 100, 0),
    ('baldurs gate', 800 * 100, 0),
    ('world of warcraft', 10000 * 100, 0),
    ('pokemon', 100000 * 100, 0),
    ('magic', 5000 * 100, 0);
END;
$$;