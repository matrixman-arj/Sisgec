package com.cursomarajoara.sisgec.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.cursomarajoara.sisgec.enuns.Turno;
import com.cursomarajoara.sisgec.validation.SKU;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long codigo;
	
	@SKU
	@NotBlank(message = " O campo sku é obrigatório!")
	private String sku;
	
	@NotBlank(message = " O campo nome é obrigatório!")
	private String nome;
	
	@Column(name = "carga_horaria")
	@NotNull(message = " O campo Carga Horária é obrigatório!")
	private Integer cargaHoraria;	
	
	@NotBlank(message = " O campo descrição é obrigatório!")
	@Size(min = 1, max = 50, message = " O tamanho do campo descrição deve estar entre 1 e 50")
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = " O campo turno é obrigatório!")
	private Turno turno;
	
	@NotNull(message = " O campo tipo é obrigatório!")
	@ManyToOne
	@JoinColumn(name = "codigo_tipoCurso")	
	private TipoCurso tipoCurso;
	

	@Size(min = 1, message = " Selecione pelo menos uma disciplina")
	@ManyToMany
	@JoinTable(name = "curso_disciplina", joinColumns = @JoinColumn(name = "codigo_curso")
				, inverseJoinColumns = @JoinColumn(name = "codigo_disciplina"))	
	private List<Disciplina> disciplinas;
	
	@NotNull(message = " O campo valor é obtigatório")
	@DecimalMin("0.01")
	@DecimalMax(value = "9999999.99", message = "O Valor do curso deve ser menor que R$ 9.999.999,99")
	private BigDecimal valor;
	
	private String foto;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private boolean novaFoto;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}	
	
	public TipoCurso getTipoCurso() {
		return tipoCurso;
	}
	public void setTipoCurso(TipoCurso tipoCurso) {
		this.tipoCurso = tipoCurso;
	}
	

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	
	public BigDecimal getValor() {
		return valor;
	}	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}	
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "foto-mock.png";
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(this.foto);
	}
	
	public boolean isNovo() {
		return codigo == null;
	}
	
	public boolean isNovaFoto() {
		return novaFoto;
	}

	public void setNovaFoto(boolean novaFoto) {
		this.novaFoto = novaFoto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Curso other = (Curso) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

		
}
