package com.cursomarajoara.sisgec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cursomarajoara.sisgec.exception.NomeTipoCursoJaCadastradoException;
import com.cursomarajoara.sisgec.model.TipoCurso;
import com.cursomarajoara.sisgec.service.CadastroTipoCursoService;

@Controller
@RequestMapping("/tiposCursos")
public class TiposCursosController {	
	
	@Autowired 
	private CadastroTipoCursoService cadastroTipoCursoService;

		
	@RequestMapping("/novo")
	public ModelAndView  novo(TipoCurso tipoCurso) {
		return new ModelAndView("curso/tipoCurso/CadastroTipoCurso");		
		
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST )
	public ModelAndView  cadastrar(@Valid TipoCurso tipoCurso, BindingResult result, Model model, RedirectAttributes attributes) {
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
	
}
