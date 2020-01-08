package com.cursomarajoara.sisgec.venda;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.cursomarajoara.sisgec.model.Curso;

public class TabelaItensVendaTest {
	
	private TabelaItensVenda tabelaItensVenda;
	
	@Before
	public void setUp() {
		this.tabelaItensVenda = new TabelaItensVenda();
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		Curso curso = new Curso();
		BigDecimal valor = new BigDecimal("8.90");
		curso.setValor(valor);
		
		tabelaItensVenda.adicionarItem(curso, 1);
		
		assertEquals(valor, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {
		Curso c1 = new Curso();
		BigDecimal v1 = new BigDecimal("8.90");
		c1.setValor(v1);
		
		Curso c2 = new Curso();
		BigDecimal v2 = new BigDecimal("4.99");
		c2.setValor(v2);
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.adicionarItem(c2, 2);
		
		assertEquals(new BigDecimal("18.88"), tabelaItensVenda.getValorTotal());
	}
}
