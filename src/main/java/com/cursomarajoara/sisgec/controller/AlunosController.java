package com.cursomarajoara.sisgec.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cursomarajoara.sisgec.model.Aluno;

@Controller
public class AlunosController {
	
	@RequestMapping("/alunos/novo")
	public String novo(Aluno aluno) {		
		return "aluno/CadastroAluno";
	}
	
	@RequestMapping(value = "/alunos/novo", method = RequestMethod.POST )
	public String cadastrar(@Valid Aluno aluno, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {					
			return novo(aluno);
		}
		
		//Salvar no Banco de dados.....		
		attributes.addFlashAttribute("mensagem", "Aluno salvo com sucesso! ");
		System.out.println(">>>>>>>>> Nome: " + aluno.getNome());
		return "redirect:/alunos/novo";
	}
	
	@RequestMapping("/alunos/cadastro")
	public String cadastro() {
		return "aluno/cadastro-produto";
	}
}
