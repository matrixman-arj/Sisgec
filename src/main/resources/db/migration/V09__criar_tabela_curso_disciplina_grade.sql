CREATE TABLE grade (
    codigo BIGINT(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE curso_disciplina (
    codigo_curso BIGINT(20) NOT NULL,
    codigo_disciplina BIGINT(20) NOT NULL,
    PRIMARY KEY (codigo_curso, codigo_disciplina),
    FOREIGN KEY (codigo_curso) REFERENCES curso(codigo_curso),
    FOREIGN KEY (codigo_disciplina) REFERENCES disciplina(codigo_disciplina)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE disciplina_grade (
    codigo_disciplina BIGINT(20) NOT NULL,
    codigo_grade BIGINT(20) NOT NULL,
    PRIMARY KEY (codigo_disciplina, codigo_grade),
    FOREIGN KEY (codigo_disciplina) REFERENCES disciplina(codigo_disciplina),
    FOREIGN KEY (codigo_grade) REFERENCES grade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;