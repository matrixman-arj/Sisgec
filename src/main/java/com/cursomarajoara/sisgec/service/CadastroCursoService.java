package com.cursomarajoara.sisgec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.Cursos;
import com.cursomarajoara.sisgec.service.event.curso.CursoSalvoEvent;

@Service
public class CadastroCursoService {

	@Autowired
	private Cursos cursos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Curso curso) {
		cursos.save(curso);
		
		publisher.publishEvent(new CursoSalvoEvent(curso));
	}
}
