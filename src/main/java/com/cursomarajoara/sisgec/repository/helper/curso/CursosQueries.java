package com.cursomarajoara.sisgec.repository.helper.curso;

import java.util.List;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.filter.CursoFilter;

public interface CursosQueries {
	
	public List<Curso> filtrar(CursoFilter filtro);

}