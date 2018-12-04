package com.cursomarajoara.sisgec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomarajoara.sisgec.model.Aluno;
import com.cursomarajoara.sisgec.repository.Alunos;
import com.cursomarajoara.sisgec.service.exception.CpfCnpjAlunoJaCadastradoException;

@Service
public class CadastroAlunoService {

	@Autowired
	private Alunos alunos;
		
	@Transactional
	public void salvar(Aluno aluno) {
		Optional<Aluno> alunoExistente = alunos.findByDocReceita(aluno.getCpfOuCnpjSemFormatacao());
		if(alunoExistente.isPresent()) {
			throw new CpfCnpjAlunoJaCadastradoException(" CPF/CNPJ já cadastrado");
		}
		alunos.save(aluno);
		
	}
}
