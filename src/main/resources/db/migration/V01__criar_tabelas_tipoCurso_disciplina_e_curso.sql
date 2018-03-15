CREATE TABLE tipoCurso (
	codigo_tipoCurso BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50)NOT NULL,
	descricao TEXT NOT NULL
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE disciplina (
	codigo_disciplina BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50)NOT NULL,
	descricao TEXT NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE curso (
	codigo_curso BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50)NOT NULL,
	carga_horaria INTEGER,
	descricao TEXT NOT NULL,
	turno VARCHAR(50)NOT NULL,
	codigo_tipoCurso BIGINT(20) NOT NULL,
	codigo_disciplina BIGINT(20) NOT NULL,	
	valor DECIMAL(10, 2) NOT NULL,	
	FOREIGN KEY (codigo_tipoCurso) REFERENCES tipoCurso(codigo_tipoCurso),
	FOREIGN KEY (codigo_disciplina) REFERENCES disciplina(codigo_disciplina)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO tipoCurso VALUES (0,'Tecnico', 'Curso técnico de enfermagem');
INSERT INTO tipoCurso VALUES (0, 'Extra curricular', 'Curso extra curricular');

INSERT INTO disciplina VALUES (0, 'Farmacologia', 'O conhecimento técnico sobre os fármacos');
INSERT INTO disciplina VALUES (0, 'Noções de Administração', 'Noção da área administrativa');
INSERT INTO disciplina VALUES (0, 'Anatomia e Fisiologia', 'A anatomia humana é um ramo da Biologia que estuda - fisiologia é uma ciência que pesquisa as funções dos seres vivos.');
INSERT INTO disciplina VALUES (0, 'Psicologia Aplicada', 'A psicologia, por se tratar de uma ciência que lida justamente com as questões afetivas do ser humano ');