package com.cursomarajoara.sisgec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomarajoara.sisgec.model.Estado;
import com.cursomarajoara.sisgec.model.TipoCurso;

@Repository
public interface Estados extends JpaRepository<Estado, Long> {

	public Optional<TipoCurso> findByNomeIgnoreCase(String nome);
	
	
}
