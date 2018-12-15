package com.cursomarajoara.sisgec.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cursomarajoara.sisgec.model.Cidade;
import com.cursomarajoara.sisgec.repository.filter.CidadeFilter;



public interface CidadesQueries {

	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable);
	
}
