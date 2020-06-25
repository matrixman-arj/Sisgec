package com.cursomarajoara.sisgec.session;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.model.ItemVenda;

class TabelaItensVenda {
	
	private String uuid;
	private List<ItemVenda> itens = new ArrayList<>();
		
	public TabelaItensVenda(String uuid) {
		this.uuid = uuid;		
	}	

	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		
	}
	
	public void adicionarItem(Curso curso, Integer quantidade) {
		Optional<ItemVenda> itemVendaOptional = buscarItemPorCurso(curso);
		
		ItemVenda itemVenda = null;
		if (itemVendaOptional.isPresent()) {
			itemVenda = itemVendaOptional.get();
			itemVenda.setQuantidade(itemVenda.getQuantidade() + quantidade);
		
		} else {
			itemVenda = new ItemVenda();
			itemVenda.setCurso(curso);
			itemVenda.setQuantidade(quantidade);
			itemVenda.setValorUnitario(curso.getValor());
			itens.add(0, itemVenda);			
		}		
	}
	
	public void alterarQuantidadeItens(Curso curso, Integer quantidade) {
		ItemVenda itemVenda = buscarItemPorCurso(curso).get();
		itemVenda.setQuantidade(quantidade);
	}
	
	public void excluirItem(Curso curso) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getCurso().equals(curso))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	public int total() {
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		
		return itens;
	}
	
	private Optional<ItemVenda> buscarItemPorCurso(Curso curso) {
		return itens.stream()
				.filter(i -> i.getCurso().equals(curso))
				.findAny();		
	}
	
	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		TabelaItensVenda other = (TabelaItensVenda) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
