package com.cursomarajoara.sisgec.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomarajoara.sisgec.exception.NomeTipoCursoJaCadastradoException;
import com.cursomarajoara.sisgec.model.TipoCurso;
import com.cursomarajoara.sisgec.repository.TiposCursos;
import com.cursomarajoara.sisgec.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroTipoCursoService {

	@Autowired
	private TiposCursos tiposCursos;
	
	@Transactional
	public TipoCurso salvar(TipoCurso tipoCurso) {
		Optional<TipoCurso> tipoCursoOptional = tiposCursos.findByNomeIgnoreCase(tipoCurso.getNome());
		if(tipoCursoOptional.isPresent()) {
			throw new NomeTipoCursoJaCadastradoException(" Nome do tipo de estilo já cadastrado!");
			
		}
		return tiposCursos.saveAndFlush(tipoCurso);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		try {			
			tiposCursos.delete(codigo);
			tiposCursos.flush();			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o tipo de curso, pois já está atrelado a um curso. ");
		}
	}
}
