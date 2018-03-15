package com.cursomarajoara.sisgec.enuns;

public enum Turno {
	
	MANHA("Manhã"),
	TARDE("Tarde"),
	NOITE("Noite");
	
	private String descricao;
	
	Turno(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
