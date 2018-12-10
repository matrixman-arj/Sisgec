package com.cursomarajoara.sisgec.repository.helper.aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cursomarajoara.sisgec.model.Aluno;
import com.cursomarajoara.sisgec.repository.filter.AlunoFilter;

public interface AlunosQueries {
	
	public Page<Aluno> filtrar(AlunoFilter filtro, Pageable pageable);

}
