package com.cursomarajoara.sisgec.repository.helper.curso;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cursomarajoara.sisgec.dto.CursoDTO;
import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.filter.CursoFilter;

public interface CursosQueries {
	
	public Page<Curso> filtrar(CursoFilter filtro, Pageable pageable);
	
	public List<CursoDTO> porSkuOuNome(String skuOuNome);
	
	public Curso buscarComDisciplinas(Long codigo);

}
