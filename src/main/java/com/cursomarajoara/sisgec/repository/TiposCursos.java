package com.cursomarajoara.sisgec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomarajoara.sisgec.model.TipoCurso;
import com.cursomarajoara.sisgec.repository.helper.curso.tipo.TiposCursosQueries;

@Repository
public interface TiposCursos extends JpaRepository<TipoCurso, Long>, TiposCursosQueries  {

	public Optional<TipoCurso> findByNomeIgnoreCase(String nome);
	
	
}
