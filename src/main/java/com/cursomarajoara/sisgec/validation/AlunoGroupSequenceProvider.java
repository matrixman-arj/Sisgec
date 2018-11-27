package com.cursomarajoara.sisgec.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.cursomarajoara.sisgec.model.Aluno;

public class AlunoGroupSequenceProvider implements DefaultGroupSequenceProvider<Aluno> {

	@Override
	public List<Class<?>> getValidationGroups(Aluno aluno) {
		 List<Class<?>> grupos = new ArrayList<>();
		 grupos.add(Aluno.class);
		 
		 if (isPessoaSelecionada(aluno)) {
			grupos.add(aluno.getTipoPessoa().getGrupo()); 
		 }
		 
		return grupos;
	}

	private boolean isPessoaSelecionada(Aluno aluno) {
		
		return aluno != null && aluno.getTipoPessoa() != null;
	}

}
