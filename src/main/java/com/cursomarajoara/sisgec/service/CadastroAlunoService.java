package com.cursomarajoara.sisgec.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.model.Aluno;
import com.cursomarajoara.sisgec.repository.Alunos;
import com.cursomarajoara.sisgec.service.exception.CpfCnpjAlunoJaCadastradoException;
import com.cursomarajoara.sisgec.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroAlunoService {

	@Autowired
	private Alunos alunos;
		
	@Transactional
	public void salvar(Aluno aluno) {
		Optional<Aluno> alunoExistente = alunos.findByDocReceita(aluno.getCpfOuCnpjSemFormatacao());
		if(alunoExistente.isPresent() && !alunoExistente.get().equals(aluno)) {
			throw new CpfCnpjAlunoJaCadastradoException(" CPF/CNPJ já cadastrado");
		}
		if (!aluno.isNovo() || !StringUtils.isEmpty(aluno.getEndereco().getCidade())) {
			aluno.setEndereco(alunoExistente.get().getEndereco());
		}
				
		alunos.save(aluno);		
	}
	
	@Transactional
	public void excluir(Long codigo) {
		try {			
			alunos.delete(codigo);
			alunos.flush();			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o curso, pois já está atrelado a uma turma. ");
			}
		}


}
