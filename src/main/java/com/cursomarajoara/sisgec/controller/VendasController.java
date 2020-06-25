package com.cursomarajoara.sisgec.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	private ModelAndView mvTabelaItensVenda(String uuid) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		return mv;
	}

}
