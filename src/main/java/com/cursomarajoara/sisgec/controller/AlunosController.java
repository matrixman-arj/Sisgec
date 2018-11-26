package com.cursomarajoara.sisgec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cursomarajoara.sisgec.enuns.TipoPessoa;
import com.cursomarajoara.sisgec.model.Aluno;
import com.cursomarajoara.sisgec.repository.Estados;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	
	@Autowired
	private Estados estados;
	
	@RequestMapping("novo")
	public ModelAndView novo(Aluno aluno) {
		ModelAndView mv = new ModelAndView("aluno/CadastroAluno");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@RequestMapping(value = "/alunos/novo", method = RequestMethod.POST )
	public String cadastrar(@Valid Aluno aluno, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {					
			return null;
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
