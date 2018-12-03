package com.cursomarajoara.sisgec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomarajoara.sisgec.model.Aluno;

@Repository
public interface Alunos extends JpaRepository<Aluno, Long> {		

}
