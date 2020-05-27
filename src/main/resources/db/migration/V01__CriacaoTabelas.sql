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

CREATE TABLE saldo
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    produto          BIGINT NOT NULL,
    saldo_dados      INT    NOT NULL,
    saldo_minutos    INT    NOT NULL,
    saldo_sms        INT    NOT NULL,
    data_atualizacao DATETIME DEFAULT NOW(),
    FOREIGN KEY (produto) REFERENCES produto (id)
);

CREATE TRIGGER trigger_produto_ins
    AFTER INSERT
    ON produto
    FOR EACH ROW
BEGIN
    IF NOT EXISTS(SELECT id FROM saldo WHERE saldo.produto = NEW.id)
    THEN
        INSERT INTO saldo (produto, saldo_dados, saldo_minutos, saldo_sms)
        VALUES (NEW.id, NEW.qtd_dados, NEW.qtd_minutos, NEW.qtd_sms);
    END IF;

    IF NOT EXISTS(SELECT id FROM fatura WHERE produto_id = NEW.id)
    THEN
        INSERT INTO fatura (produto_id, data_emissao, mes_referencia, periodo_inicio, periodo_fim, valor)
        VALUES (NEW.id, NOW(), '2020-05-01', NOW(), '2020-05-31', 0.00);
    END IF;
END;

CREATE TRIGGER trigger_registro_ins
    AFTER INSERT
    ON registro_cdr
    FOR EACH ROW
BEGIN
    IF NOT EXISTS(SELECT id FROM saldo WHERE saldo.produto = NEW.origem)
    THEN
        BEGIN
            DECLARE saldo_voz_calc INT;
            DECLARE saldo_dados_calc INT;
            DECLARE saldo_sms_calc INT;

            SELECT CASE
                       WHEN NEW.tipo_registro = 0 AND p.qtd_minutos - NEW.consumo IS NOT NULL
                           THEN p.qtd_minutos - NEW.consumo
                       WHEN NEW.tipo_registro = 0 AND p.qtd_minutos - NEW.consumo < 0
                           THEN 0
                       ELSE p.qtd_minutos
                       END AS saldo_voz,
                   CASE
                       WHEN NEW.tipo_registro = 1 AND p.qtd_dados - NEW.consumo IS NOT NULL
                           THEN p.qtd_dados - NEW.consumo
                       WHEN NEW.tipo_registro = 1 AND p.qtd_dados - NEW.consumo < 0
                           THEN 0
                       ELSE
                           p.qtd_dados
                       END AS saldo_dados,
                   CASE
                       WHEN NEW.tipo_registro = 2 AND p.qtd_sms - NEW.consumo IS NOT NULL
                           THEN p.qtd_sms - NEW.consumo
                       WHEN NEW.tipo_registro = 2 AND p.qtd_sms - NEW.consumo < 0
                           THEN 0
                       ELSE p.qtd_sms
                       END AS saldo_sms
            INTO saldo_voz_calc, saldo_dados_calc, saldo_sms_calc
            FROM produto p
            WHERE p.id = NEW.origem;

            INSERT INTO saldo (produto, saldo_dados, saldo_minutos, saldo_sms)
            VALUES (NEW.origem, saldo_dados_calc, saldo_voz_calc, saldo_sms_calc);
        END;
    ELSE
        BEGIN
            DECLARE saldo_voz_att INT;
            DECLARE saldo_dados_att INT;
            DECLARE saldo_sms_att INT;

            SELECT CASE
                       WHEN NEW.tipo_registro = 0 AND s.saldo_minutos - NEW.consumo IS NOT NULL
                           THEN s.saldo_minutos - NEW.consumo
                       WHEN NEW.tipo_registro = 0 AND s.saldo_minutos - NEW.consumo < 0
                           THEN 0
                       ELSE s.saldo_minutos
                       END AS saldo_voz,
                   CASE
                       WHEN NEW.tipo_registro = 1 AND s.saldo_dados - NEW.consumo IS NOT NULL
                           THEN s.saldo_dados - NEW.consumo
                       WHEN NEW.tipo_registro = 1 AND s.saldo_dados - NEW.consumo < 0
                           THEN 0
                       ELSE
                           s.saldo_dados
                       END AS saldo_dados,
                   CASE
                       WHEN NEW.tipo_registro = 2 AND s.saldo_sms - NEW.consumo IS NOT NULL
                           THEN s.saldo_sms - NEW.consumo
                       WHEN NEW.tipo_registro = 2 AND s.saldo_sms - NEW.consumo < 0
                           THEN 0
                       ELSE s.saldo_sms
                       END AS saldo_sms
            INTO saldo_voz_att, saldo_dados_att, saldo_sms_att
            FROM saldo s
            WHERE s.produto = NEW.origem;

            UPDATE saldo
            SET saldo_dados      = saldo_dados_att,
                saldo_minutos    = saldo_voz_att,
                saldo_sms        = saldo_sms_att,
                data_atualizacao = NOW()
            WHERE produto = NEW.origem;
        END;
    END IF;
END;

