package com.cursomarajoara.sisgec.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.Cursos;
import com.cursomarajoara.sisgec.session.TabelasItensSessions;

@Controller
@RequestMapping("/vendas")
public class VendasController {
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private TabelasItensSessions tabelaItens;
	
	@GetMapping("/nova")
	public ModelAndView nova() {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		mv.addObject("uuid", UUID.randomUUID().toString());
		return mv;
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCurso, String uuid) {
		Curso curso = cursos.findOne(codigoCurso);
		tabelaItens.adicionarItem(uuid, curso, 1);
		return mvTabelaItensVenda(uuid);
	}
	
	@PutMapping("/item/{codigoCurso}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoCurso") Curso curso
			, Integer quantidade, String uuid) {
		tabelaItens.alterarQuantidadeItens(uuid, curso, quantidade);
		return mvTabelaItensVenda(uuid);				
	}
	
	@DeleteMapping("/item/{uuid}/{codigoCurso}")
	public ModelAndView excluirItem(@PathVariable("codigoCurso") Curso curso
			, @PathVariable String uuid) {
		tabelaItens.excluirItem(uuid, curso);
		return mvTabelaItensVenda(uuid);	
	}
/*2.3 -> Aqui onde está retornando a tabela de itens...  */
	private ModelAndView mvTabelaItensVenda(String uuid) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");/*<-2.3.4 Está sendo mandado para a tabela itens de venda .html, então vamos abri-la*/
		mv.addObject("itens", tabelaItens.getItens(uuid));
		
		/*2.3.1 -> Adicionamos um objeto, colocando o valor total, 
		 * recuperando da tabela de itens, para esse ID... 
		 * Criamos agora esse método que ainda nãp existe, no arquivo TabelaItensSessions*/
		mv.addObject("valorTotal", tabelaItens.getValorTotal(uuid));/*<-2.3.3 Esse valor total...*/
		return mv;
	}

}
