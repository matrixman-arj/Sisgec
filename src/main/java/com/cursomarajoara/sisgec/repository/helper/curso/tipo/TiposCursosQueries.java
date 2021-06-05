package com.cursomarajoara.sisgec.repository.helper.curso.tipo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cursomarajoara.sisgec.model.TipoCurso;
import com.cursomarajoara.sisgec.repository.filter.TipoCursoFilter;

public interface TiposCursosQueries {
	
	public Page<TipoCurso> filtrar(TipoCursoFilter filtro, Pageable pageable);

}
