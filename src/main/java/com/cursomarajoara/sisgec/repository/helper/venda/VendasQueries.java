package com.cursomarajoara.sisgec.repository.helper.venda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cursomarajoara.sisgec.model.Venda;
import com.cursomarajoara.sisgec.repository.filter.VendaFilter;

public interface VendasQueries {
	
	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable);
	
	public Venda buscarComItens(Long codigo);

}
