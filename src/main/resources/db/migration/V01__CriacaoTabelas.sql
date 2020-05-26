CREATE TABLE cliente
(
    id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    cpf VARCHAR(255) NOT NULL,
    UNIQUE (cpf)
);

CREATE TABLE produto
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero           VARCHAR(255) NOT NULL,
    qtd_dados        INT          NOT NULL,
    qtd_minutos      INT          NOT NULL,
    qtd_sms          INT          NOT NULL,
    cliente_id       BIGINT       NOT NULL,
    data_habilitacao DATETIME     DEFAULT NOW(),
    UNIQUE (numero),
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

CREATE TABLE registro_cdr
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    origem        BIGINT NOT NULL,
    destino       VARCHAR(255),
    tipo_registro INT          NOT NULL,
    consumo       BIGINT       NOT NULL,
    data_inclusao DATETIME     DEFAULT NOW(),
    FOREIGN KEY (origem) REFERENCES produto (id)
);

CREATE TABLE fatura
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    produto_id     BIGINT         NOT NULL,
    data_emissao   DATETIME       DEFAULT NOW(),
    mes_referencia DATE           NOT NULL,
    periodo_inicio DATETIME       NOT NULL,
    periodo_fim    DATETIME       NOT NULL,
    valor          DECIMAL(19, 2) NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto (id)
);

