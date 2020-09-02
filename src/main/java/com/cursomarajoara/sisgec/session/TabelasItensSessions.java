package com.cursomarajoara.sisgec.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.model.ItemVenda;

@SessionScope
@Component
public class TabelasItensSessions {
	
	private Set<TabelaItensVenda> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Curso curso, int quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(curso, quantidade);
		tabelas.add(tabela);		
	}

	public void alterarQuantidadeItens(String uuid, Curso curso, Integer quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItens(curso, quantidade);
	}

	public void excluirItem(String uuid, Curso curso) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(curso);
		
	}

	public List<ItemVenda> getItens(String uuid) {		
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	/*2.3.2 -> Onde nos retornará um buscar tabela por ID, pegando o valor total da tabela de itens*/
	public Object getValorTotal(String uuid) {
		
		return buscarTabelaPorUuid(uuid).getValorTotal();
	}

	private TabelaItensVenda buscarTabelaPorUuid(String uuid) {
		TabelaItensVenda tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensVenda(uuid));
		return tabela;
	}

}
