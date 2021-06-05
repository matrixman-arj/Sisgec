package com.cursomarajoara.sisgec.repository.filter;

import com.cursomarajoara.sisgec.model.TipoCurso;

public class TipoCursoFilter {
	
	private String nome;
	private TipoCurso tipoCurso;
	
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}
	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
	}	
		
}
