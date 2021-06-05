package com.cursomarajoara.sisgec.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cursomarajoara.sisgec.controller.page.PageWrapper;
import com.cursomarajoara.sisgec.enuns.TipoPessoa;
import com.cursomarajoara.sisgec.model.Aluno;
import com.cursomarajoara.sisgec.repository.Alunos;
import com.cursomarajoara.sisgec.repository.Estados;
import com.cursomarajoara.sisgec.repository.filter.AlunoFilter;
import com.cursomarajoara.sisgec.service.CadastroAlunoService;
import com.cursomarajoara.sisgec.service.exception.CpfCnpjAlunoJaCadastradoException;
import com.cursomarajoara.sisgec.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private CadastroAlunoService cadastroAlunoService;
			
	@Autowired
	private Alunos alunos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Aluno aluno) {
		ModelAndView mv = new ModelAndView("aluno/CadastroAluno");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(aluno);
		}
		
		try {
			cadastroAlunoService.salvar(aluno);
		} catch (CpfCnpjAlunoJaCadastradoException e) {
			result.rejectValue("docReceita", e.getMessage(), e.getMessage());
			return novo(aluno);
		}
		attributes.addFlashAttribute("mensagem", "Aluno salvo com sucesso!");
		return new ModelAndView("redirect:/alunos/novo");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(AlunoFilter alunoFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("aluno/PesquisaAlunos");
		
		mv.addObject("tiposPessoa", TipoPessoa.values());		
		
		PageWrapper<Aluno> paginaWrapper = new PageWrapper<>( alunos.filtrar(alunoFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
				
		return mv;
	}
	
	@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<Aluno> pesquisar(String nome){
		validarTamanhoNome(nome);
		return alunos.findByNomeStartingWithIgnoreCase(nome);
	}

	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e){
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo){
		try {
			cadastroAlunoService.excluir(codigo);
				
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();	
	}

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable ("codigo") Aluno aluno) {		
		ModelAndView mv = novo(aluno);
		mv.addObject(aluno);
		return mv;
	}

}
