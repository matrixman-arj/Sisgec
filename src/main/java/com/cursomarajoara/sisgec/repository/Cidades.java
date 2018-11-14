package com.cursomarajoara.sisgec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomarajoara.sisgec.model.Cidade;

public interface Cidades extends JpaRepository<Cidade,	Long> {
	
	public List<Cidade> findByEstadoCodigo(Long codigoEstado);

}
