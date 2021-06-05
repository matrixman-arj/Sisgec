package com.cursomarajoara.sisgec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cursomarajoara.sisgec.controller.page.PageWrapper;
import com.cursomarajoara.sisgec.exception.NomeTipoCursoJaCadastradoException;
import com.cursomarajoara.sisgec.model.TipoCurso;
import com.cursomarajoara.sisgec.repository.TiposCursos;
import com.cursomarajoara.sisgec.repository.filter.TipoCursoFilter;
import com.cursomarajoara.sisgec.service.CadastroTipoCursoService;
import com.cursomarajoara.sisgec.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/tiposCursos")
public class TiposCursosController {	
	
	@Autowired 
	private CadastroTipoCursoService cadastroTipoCursoService;
	
	@Autowired
	private TiposCursos tiposCursos;

		
	@RequestMapping("/novo")
	public ModelAndView novo(TipoCurso tipoCurso) {
		ModelAndView mv = new ModelAndView("curso/tipoCurso/CadastroTipoCurso");
		mv.addObject("tiposCursos", tiposCursos.findAll());
		return mv;
	}
	
	@RequestMapping(value = {"/novo", "{\\d+}" }, method = RequestMethod.POST )
	public ModelAndView cadastrar(@Valid TipoCurso tipoCurso, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {					
			return novo(tipoCurso);
		}		
		
		try {
			cadastroTipoCursoService.salvar(tipoCurso);
		} catch (NomeTipoCursoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(tipoCurso);
		}
		
		attributes.addFlashAttribute("mensagem", "Tipo de curso salvo com sucesso! ");
		return new ModelAndView("redirect:/tiposCursos/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid TipoCurso tipoCurso, BindingResult result) {
		if (result.hasErrors()) {			
			return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
			
		}		
		tipoCurso = cadastroTipoCursoService.salvar(tipoCurso);		
		return ResponseEntity.ok(tipoCurso);		
	}
	
	@GetMapping
	public ModelAndView pesquisar(TipoCursoFilter tipoCursoFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("curso/tipoCurso/PesquisaTiposCursos");
		
		
		
		PageWrapper<TipoCurso> paginaWrapper = new PageWrapper<>(tiposCursos.filtrar(tipoCursoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Long codigo){
		try {
			cadastroTipoCursoService.excluir(codigo);
			
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable ("codigo") Long codigo) {
		
		TipoCurso tipoCurso = tiposCursos.findOne(codigo);
		ModelAndView mv = novo(tipoCurso);		
		mv.addObject(tipoCurso);
		
		System.out.println(">> Obj: " + tipoCurso);
		System.out.println(">> Código: " + tipoCurso.getCodigo());
		System.out.println(">> Nome: " + tipoCurso.getNome());
		System.out.println(">> Descrição: " + tipoCurso.getDescricao());
		return mv;
		
	}
	
	
}
