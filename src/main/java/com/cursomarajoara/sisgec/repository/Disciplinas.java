package com.cursomarajoara.sisgec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomarajoara.sisgec.model.Disciplina;

@Repository
public interface Disciplinas extends JpaRepository<Disciplina, Long> {
	
		

}
