package com.cursomarajoara.sisgec.repository.filter;

import com.cursomarajoara.sisgec.enuns.TipoPessoa;

public class AlunoFilter {
	
	private String nome;	
	private String docReceita;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
			
	public String getDocReceita() {
		return docReceita;
	}
	public void setDocReceita(String docReceita) {
		this.docReceita = docReceita;
	}
	
	public Object getCpfOuCnpjSemFormatacao() {
		return TipoPessoa.removerFormatacao(this.docReceita);
	}
			
}
