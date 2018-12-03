package com.cursomarajoara.sisgec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomarajoara.sisgec.model.Aluno;
import com.cursomarajoara.sisgec.repository.Alunos;

@Service
public class CadastroAlunoService {

	@Autowired
	private Alunos alunos;
		
	@Transactional
	public void salvar(Aluno aluno) {
		alunos.save(aluno);
		
	}
}
