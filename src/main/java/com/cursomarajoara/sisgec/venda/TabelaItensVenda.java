package com.cursomarajoara.sisgec.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.model.ItemVenda;

public class TabelaItensVenda {
	
	private List<ItemVenda> itens = new ArrayList<>();
	
	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		
	}
	
	public void adicionarItem(Curso curso, Integer quantidade) {
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setCurso(curso);
		itemVenda.setQuantidade(quantidade);
		itemVenda.setValorUnitario(curso.getValor());
		
		itens.add(itemVenda);
	}
	

}
