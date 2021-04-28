package com.cursomarajoara.sisgec.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.Cursos;
import com.cursomarajoara.sisgec.service.event.curso.CursoSalvoEvent;
import com.cursomarajoara.sisgec.service.exception.ImpossivelExcluirEntidadeException;
import com.cursomarajoara.sisgec.storage.FotoStorage;

@Service
public class CadastroCursoService {

	@Autowired
	private Cursos cursos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Curso curso) {
		cursos.save(curso);
		
		publisher.publishEvent(new CursoSalvoEvent(curso));
	}
	
	@Transactional
	public void excluir(Curso curso) {
		try {
			String foto = curso.getFoto();
			cursos.delete(curso);
			cursos.flush();
			fotoStorage.excluir(foto);
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o curso, pois já está atrelado a uma turma. ");
		}
	}
}
