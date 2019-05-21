package com.cursomarajoara.sisgec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cursomarajoara.sisgec.controller.page.PageWrapper;
import com.cursomarajoara.sisgec.enuns.Turno;
import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.repository.Cursos;
import com.cursomarajoara.sisgec.repository.Disciplinas;
import com.cursomarajoara.sisgec.repository.TiposCursos;
import com.cursomarajoara.sisgec.repository.filter.CursoFilter;
import com.cursomarajoara.sisgec.service.CadastroCursoService;

@Controller
@RequestMapping("/cursos")
public class CursosController {
	
	@Autowired
	private TiposCursos tiposCursos;
	
	@Autowired
	private Disciplinas disciplinas;
	
	
	@Autowired 
	private CadastroCursoService cadastroCursoService;
	
	@Autowired
	private Cursos cursos;

		
	@RequestMapping("/novo")
	public ModelAndView  novo(Curso curso) {
		ModelAndView mv = new ModelAndView("curso/CadastroCurso");
		mv.addObject("turnos", Turno.values());
		mv.addObject("tiposCursos", tiposCursos.findAll());
		mv.addObject("disciplinas", disciplinas.findAll());
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST )
	public ModelAndView  cadastrar(@Valid Curso curso, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {			
			return novo(curso);
		}		
		
		cadastroCursoService.salvar(curso);
		attributes.addFlashAttribute("mensagem", "Curso salvo com sucesso! ");
		return new ModelAndView("redirect:/cursos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CursoFilter cursoFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("curso/PesquisaCursos");
		
		mv.addObject("tiposCursos", tiposCursos.findAll());
		mv.addObject("turnos", Turno.values());
		mv.addObject("disciplinas", disciplinas.findAll());
		
		PageWrapper<Curso> paginaWrapper = new PageWrapper<>( cursos.filtrar(cursoFilter, pageable), httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
				
		return mv;
	}
	
}
