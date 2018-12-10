package com.cursomarajoara.sisgec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomarajoara.sisgec.model.Aluno;
import com.cursomarajoara.sisgec.repository.helper.aluno.AlunosQueries;

@Repository
public interface Alunos extends JpaRepository<Aluno, Long>, AlunosQueries {

	
}
