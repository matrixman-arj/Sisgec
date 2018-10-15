package com.cursomarajoara.sisgec.repository.filter;

import com.cursomarajoara.sisgec.enuns.Turno;
import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.model.Disciplina;
import com.cursomarajoara.sisgec.model.TipoCurso;

public class CursoFilter {
	
	private String nome;	
	private Disciplina disciplina;
	private TipoCurso tipoCurso;
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
	
	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}
	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
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
