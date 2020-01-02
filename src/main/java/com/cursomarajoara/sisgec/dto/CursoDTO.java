package com.cursomarajoara.sisgec.dto;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

public class CursoDTO {
	
	private Long codigo;
	private String sku;
	private String nome;
	private BigDecimal valor;
	private String foto;
	
	public CursoDTO(Long codigo, String sku, String nome, BigDecimal valor, String foto) {
		
		this.codigo = codigo;
		this.sku = sku;
		this.nome = nome;
		this.valor = valor;
		this.foto = StringUtils.isEmpty(foto) ? "foto-mock.png" : foto;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	
		
}
