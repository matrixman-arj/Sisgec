package com.cursomarajoara.sisgec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomarajoara.sisgec.model.Venda;
import com.cursomarajoara.sisgec.repository.helper.venda.VendasQueries;

public interface Vendas extends JpaRepository<Venda, Long>, VendasQueries {

}
