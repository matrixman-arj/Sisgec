package com.cursomarajoara.sisgec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomarajoara.sisgec.model.Curso;

@Repository
public interface Cursos extends JpaRepository<Curso, Long> {
	
		

}
