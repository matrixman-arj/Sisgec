package com.cursomarajoara.sisgec.repository.filter;

import com.cursomarajoara.sisgec.enuns.Turno;
import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.model.Disciplina;
import com.cursomarajoara.sisgec.model.TipoCurso;

public class CursoFilter {
	
	private String nome;	
	private Disciplina disciplina;
	private TipoCurso tipo;
	private Turno turno;
	private Curso curso;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
		
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}
