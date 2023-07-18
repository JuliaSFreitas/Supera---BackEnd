CREATE TABLE conta
(
    id_conta IDENTITY NOT NULL PRIMARY KEY,
    nome_responsavel VARCHAR(50) NOT NULL
);


CREATE TABLE transferencia
(
    id IDENTITY NOT NULL PRIMARY KEY,
    data_transferencia TIMESTAMP WITH TIME ZONE NOT NULL,
    valor NUMERIC (20,2) NOT NULL,
    tipo VARCHAR(15) NOT NULL,
    nome_operador_transacao VARCHAR (50),
    conta_id INT NOT NULL,

        CONSTRAINT FK_CONTA
        FOREIGN KEY (conta_id)
        REFERENCES conta(id_conta)
);

INSERT INTO conta (id_conta, nome_responsavel) VALUES (1,'Fulano');
INSERT INTO conta (id_conta, nome_responsavel) VALUES (2,'Sicrano');

INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (1,'2019-01-01 12:00:00+03',30895.46,'DEPOSITO', null, 1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (2,'2019-02-03 09:53:27+03',12.24,'DEPOSITO', null,2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (3,'2019-05-04 08:12:45+03',-500.50,'SAQUE', null,1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (4,'2019-08-07 08:12:45+03',-530.50,'SAQUE', null,2);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (5,'2020-06-08 10:15:01+03',3241.23,'TRANSFERENCIA', 'Beltrano',1);
INSERT INTO transferencia (id,data_transferencia, valor, tipo, nome_operador_transacao, conta_id) VALUES (6,'2021-04-01 12:12:04+03',25173.09,'TRANSFERENCIA', 'Ronnyscley',2);

------- FUNÇÕES DE CONSULTA A TODO O HISTÓRICO DE TRANSFERÊNCIA --------
SELECT tf.data_transferencia, tf.valor, tf.tipo, tf.nome_operador_transacao
FROM conta ct INNER JOIN transferencia tf
ON ct.id_conta = tf.conta_id

CREATE FUNCTION fn_historico(@cod INT)
RETURNS @tabela TABLE (
data_transferencia			DATETIMEOFFSET,
valor						NUMERIC (20,2),
tipo						VARCHAR(15),
nome_operador_transacao		VARCHAR(50)
)
AS
BEGIN
	INSERT INTO @tabela(data_transferencia, valor, tipo, nome_operador_transacao)
		SELECT tf.data_transferencia, tf.valor, tf.tipo, tf.nome_operador_transacao
		FROM conta ct LEFT OUTER JOIN transferencia tf
		ON ct.id_conta = tf.conta_id
		WHERE ct.id_conta = @cod
	RETURN 
END
 
select * from fn_historico(1)

------- FUNÇÕES DE CONSULTA A PERÍODO DE TEMPO --------
CREATE FUNCTION fn_periodo(@cod INT, @incio CHAR(10),@fim CHAR(10))
RETURNS @tabela TABLE (
data_transferencia			DATETIMEOFFSET,
valor						NUMERIC (20,2),
tipo						VARCHAR(15),
nome_operador_transacao		VARCHAR(50)
)
AS
BEGIN
	INSERT INTO @tabela(data_transferencia, valor, tipo, nome_operador_transacao)
		SELECT tf.data_transferencia, tf.valor, tf.tipo, tf.nome_operador_transacao
		FROM conta ct LEFT OUTER JOIN transferencia tf
		ON ct.id_conta = tf.conta_id
		WHERE ct.id_conta = @cod
			  AND tf.data_transferencia BETWEEN @incio AND @fim
	RETURN 
END
 
select * from fn_periodo(1, '2019-01-01', '2019-06-01')

------- FUNÇÕES DE CONSULTA POR NOME --------
CREATE FUNCTION fn_nome(@cod INT, @nome VARCHAR(50))
RETURNS @tabela TABLE (
data_transferencia			DATETIMEOFFSET,
valor						NUMERIC (20,2),
tipo						VARCHAR(15),
nome_operador_transacao		VARCHAR(50)
)
AS
BEGIN
	INSERT INTO @tabela(data_transferencia, valor, tipo, nome_operador_transacao)
		SELECT tf.data_transferencia, tf.valor, tf.tipo, tf.nome_operador_transacao
		FROM conta ct LEFT OUTER JOIN transferencia tf
		ON ct.id_conta = tf.conta_id
		WHERE ct.id_conta = @cod
			  AND tf.nome_operador_transacao = @nome
	RETURN 
END
 
select * from fn_nome(1, 'Beltrano')

------- FUNÇÕES DE CONSULTA POR NOME E PERIODO--------
CREATE FUNCTION fn_nomeperiodo(@cod INT, @nome VARCHAR(50), @incio CHAR(10),@fim CHAR(10))
RETURNS @tabela TABLE (
data_transferencia			DATETIMEOFFSET,
valor						NUMERIC (20,2),
tipo						VARCHAR(15),
nome_operador_transacao		VARCHAR(50)
)
AS
BEGIN
	INSERT INTO @tabela(data_transferencia, valor, tipo, nome_operador_transacao)
		SELECT tf.data_transferencia, tf.valor, tf.tipo, tf.nome_operador_transacao
		FROM conta ct LEFT OUTER JOIN transferencia tf
		ON ct.id_conta = tf.conta_id
		WHERE ct.id_conta = @cod
			  AND tf.nome_operador_transacao = @nome
			  AND tf.data_transferencia BETWEEN @incio AND @fim
	RETURN 
END
 
select * from fn_nomeperiodo(1, 'Beltrano', '2020-01-01', '2020-09-01')
