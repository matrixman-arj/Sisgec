package com.cursomarajoara.sisgec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomarajoara.sisgec.exception.NomeTipoCursoJaCadastradoException;
import com.cursomarajoara.sisgec.model.TipoCurso;
import com.cursomarajoara.sisgec.repository.TiposCursos;

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
}
