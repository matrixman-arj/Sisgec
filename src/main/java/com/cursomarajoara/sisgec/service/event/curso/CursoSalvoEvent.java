package com.cursomarajoara.sisgec.service.event.curso;

import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.model.Curso;



public class CursoSalvoEvent {
	
	private Curso curso;

	public CursoSalvoEvent(Curso curso) {		
		this.curso = curso;
	}

	public Curso getCurso() {
		return curso;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(curso.getFoto());
	}

}
