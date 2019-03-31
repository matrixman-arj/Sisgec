CREATE TABLE IF NOT EXISTS estado (
    codigo BIGINT(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sigla VARCHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS cidade (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    codigo_estado BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_estado) REFERENCES estado(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (1,'Acre', 'AC');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (2,'Alagoas', 'AL');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (3,'Amapá', 'AP');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (4,'Amazonas', 'AM');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (5,'Bahia', 'BA');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (6,'Ceará', 'CE');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (7,'Distrito Federal', 'DF');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (8,'Espírito Santos', 'ES');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (9,'Goiás', 'GO');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (10,'Maranhão', 'MA');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (11,'Mato Grosso', 'MT');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (12,'Mato Grosso do Sul', 'MS');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (13,'Minas Gerais', 'MG');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (14,'Pará', 'PA');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (15,'Paraíba', 'PB');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (16,'Paraná', 'PR');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (17,'Pernambuco', 'PE');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (18,'Piauí', 'PI');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (19,'Rio de Janeiro', 'RJ');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (20,'Rio Grande do Norte', 'RN');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (21,'Rio Grande do Sul', 'RS');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (22,'Rondônia', 'RO');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (23,'Roraima', 'RR');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (24,'Santa Catarina', 'SC');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (25,'São Paulo', 'SP');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (26,'Sergipe', 'SE');
INSERT IGNORE INTO estado (codigo, nome, sigla) VALUES (27,'Tocantins', 'TO');


INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Rio Branco', 1);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Cruzeiro do Sul', 1);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Maceió', 2);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Macapá', 3);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Manaus', 4);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Salvador', 5);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Porto Seguro', 5);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Santana', 5);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Fortaleza', 6);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Brasília', 7);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Vitória', 8);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Goiânia', 9);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Itumbiara', 9);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Novo Brasil', 9);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('São Luís', 10);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Cuiabá', 11);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Campo Grande', 12);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Belo Horizonte', 13);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Uberlândia', 13);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Montes Claros', 13);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Belém', 14);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('João Pessoa', 15);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Curitiba', 16);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Recife', 17);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Teresina', 18);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Rio de Janeiro', 19);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Natal', 20);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Porto Alegre', 21);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Porto Velho', 22);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Boa Vista', 23);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Florianópolis', 24);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Criciúma', 24);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Camboriú', 24);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Lages', 24);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('São Paulo', 25);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Ribeirão Preto', 25);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Campinas', 25);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Santos', 25);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Aracajú', 26);
INSERT IGNORE INTO cidade (nome, codigo_estado) VALUES ('Palmas', 27);
