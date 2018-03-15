package com.cursomarajoara.sisgec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.Cursos;

@Service
public class CadastroCursoService {

	@Autowired
	private Cursos cursos;
	
	@Transactional
	public void salvar(Curso curso) {
		cursos.save(curso);
	}
}
