package com.cursomarajoara.sisgec.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tipoCurso")
public class TipoCurso implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_tipoCurso;
	
	@NotBlank(message = " O campo nome é obrigatório!")
	private String nome;	
	
	@OneToMany(mappedBy = "tipoCurso")
	private List<Curso> cursos;
	
	@NotBlank(message = " O campo descrição é obrigatório!")
	@Size(min = 1, max = 50, message = " O tamanho do campo descrição deve estar entre 1 e 50")
	private String descricao;	
		
	public Long getCodigo_tipoCurso() {
		return codigo_tipoCurso;
	}
	public void setCodigo_tipoCurso(Long codigo_tipoCurso) {
		this.codigo_tipoCurso = codigo_tipoCurso;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
		
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo_tipoCurso == null) ? 0 : codigo_tipoCurso.hashCode());
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
		TipoCurso other = (TipoCurso) obj;
		if (codigo_tipoCurso == null) {
			if (other.codigo_tipoCurso != null)
				return false;
		} else if (!codigo_tipoCurso.equals(other.codigo_tipoCurso))
			return false;
		return true;
	}
	
	
}
