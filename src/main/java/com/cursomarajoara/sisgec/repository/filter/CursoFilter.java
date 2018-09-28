package com.cursomarajoara.sisgec.repository.filter;

import com.cursomarajoara.sisgec.model.Disciplina;
import com.cursomarajoara.sisgec.model.TipoCurso;

public class CursoFilter {
	
	private String nome;
	private String descricao;
	private Disciplina disciplina;
	private TipoCurso tipo;
	private String turno;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public TipoCurso getTipo() {
		return tipo;
	}
	public void setTipo(TipoCurso tipo) {
		this.tipo = tipo;
	}
	
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	

}
