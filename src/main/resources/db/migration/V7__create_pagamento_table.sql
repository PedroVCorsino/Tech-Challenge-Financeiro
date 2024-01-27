CREATE TABLE IF NOT EXISTS Pagamento (
    id SERIAL PRIMARY KEY,
    de_id_cobranca VARCHAR(255),
    de_idtx VARCHAR(255),
    id_pedido BIGINT NOT NULL,
    pago BOOLEAN,
    cliente VARCHAR(11) NOT NULL,
    valor VARCHAR(14) NOT NULL
);