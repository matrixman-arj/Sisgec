package com.cursomarajoara.sisgec.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_disciplina;
	
	private String nome;
	
	private String descricao;
	
//	@OneToMany(mappedBy = "disciplina")
//	private List<Curso> cursos;	

	@ManyToMany
	@JoinTable(name = "disciplna_grade", joinColumns = @JoinColumn(name = "codigo_disciplina"), inverseJoinColumns = @JoinColumn(name = "codigo_grade"))
	private List<Grade> grades;
	
	public Long getCodigo_disciplina() {
		return codigo_disciplina;
	}
	public void setCodigo_disciplina(Long codigo_disciplina) {
		this.codigo_disciplina = codigo_disciplina;
	}	
	
	public List<Grade> getGrades() {
		return grades;
	}
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
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

//	public List<Curso> getCursos() {
//		return cursos;
//	}
//	public void setCursos(List<Curso> cursos) {
//		this.cursos = cursos;
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo_disciplina == null) ? 0 : codigo_disciplina.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (codigo_disciplina == null) {
			if (other.codigo_disciplina != null)
				return false;
		} else if (!codigo_disciplina.equals(other.codigo_disciplina))
			return false;
		return true;
	}
	
	
	
	
}
