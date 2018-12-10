package com.cursomarajoara.sisgec.repository.filter;

import com.cursomarajoara.sisgec.enuns.TipoPessoa;

public class AlunoFilter {
	
	private String nome;
	private TipoPessoa tipoPessoa;
	private String docReceita;
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}	
	
	public String getDocReceita() {
		return docReceita;
	}
	public void setDocReceita(String docReceita) {
		this.docReceita = docReceita;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}	
			
}
