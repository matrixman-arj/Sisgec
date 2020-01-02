CREATE TABLE tipocurso (
  codigo BIGINT(20) NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  descricao TEXT NOT NULL,
  PRIMARY KEY (codigo))
ENGINE = InnoDB,
DEFAULT CHARACTER SET = utf8;


CREATE TABLE disciplina (
	codigo BIGINT(20) NOT NULL AUTO_INCREMENT,
	nome VARCHAR(50)NOT NULL,
	descricao TEXT NOT NULL,
	PRIMARY KEY (codigo))
ENGINE = InnoDB,
DEFAULT CHARACTER SET = utf8;

CREATE TABLE curso (
	codigo BIGINT(20) NOT NULL AUTO_INCREMENT,
	sku VARCHAR(50)NOT NULL,
	nome VARCHAR(50)NOT NULL,
	carga_horaria INTEGER,
	descricao TEXT NOT NULL,
	turno VARCHAR(50)NOT NULL,
	codigo_tipoCurso BIGINT(20) NOT NULL,	
	valor DECIMAL(10, 2) NOT NULL,
	PRIMARY KEY (codigo),
	FOREIGN KEY (codigo_tipoCurso) REFERENCES tipoCurso(codigo));	

	

INSERT INTO tipoCurso (codigo, nome, descricao) VALUES ('0','Tecnico', 'Curso técnico de enfermagem');
INSERT INTO tipoCurso (codigo, nome, descricao) VALUES ('0', 'Extra curricular', 'Curso extra curricular');

INSERT INTO disciplina (codigo, nome, descricao) VALUES ('0', 'Farmacologia', 'O conhecimento técnico sobre os fármacos');
INSERT INTO disciplina (codigo, nome, descricao) VALUES ('0', 'Noções de Administração', 'Noção da área administrativa');
INSERT INTO disciplina (codigo, nome, descricao) VALUES ('0', 'Anatomia e Fisiologia', 'A anatomia humana é um ramo da Biologia que estuda - fisiologia é uma ciência que pesquisa as funções dos seres vivos.');
INSERT INTO disciplina (codigo, nome, descricao) VALUES ('0', 'Psicologia Aplicada', 'A psicologia, por se tratar de uma ciência que lida justamente com as questões afetivas do ser humano ');