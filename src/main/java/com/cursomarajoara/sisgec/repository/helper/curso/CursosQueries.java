package com.cursomarajoara.sisgec.repository.helper.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.filter.CursoFilter;

public interface CursosQueries {
	
	public Page<Curso> filtrar(CursoFilter filtro, Pageable pageable);

}
