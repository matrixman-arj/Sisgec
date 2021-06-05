package com.cursomarajoara.sisgec.controller;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cursomarajoara.sisgec.controller.page.PageWrapper;
import com.cursomarajoara.sisgec.controller.validator.VendaValidator;
import com.cursomarajoara.sisgec.enuns.StatusVenda;
import com.cursomarajoara.sisgec.enuns.TipoPessoa;
import com.cursomarajoara.sisgec.mail.Mailer;
import com.cursomarajoara.sisgec.model.Curso;
import com.cursomarajoara.sisgec.model.ItemVenda;
import com.cursomarajoara.sisgec.model.Venda;
import com.cursomarajoara.sisgec.repository.Cursos;
import com.cursomarajoara.sisgec.repository.Vendas;
import com.cursomarajoara.sisgec.repository.filter.VendaFilter;
import com.cursomarajoara.sisgec.security.UsuarioSistema;
import com.cursomarajoara.sisgec.service.CadastroVendaService;
import com.cursomarajoara.sisgec.session.TabelasItensSessions;

@Controller
@RequestMapping("/vendas")
public class VendasController {
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private TabelasItensSessions tabelaItens;
	
	@Autowired
	private CadastroVendaService cadastroVendaService;
	
	@Autowired
	private VendaValidator vendaValidator;
	
	@Autowired
	private Vendas vendas;
	
	@Autowired
	private Mailer mailer;
	
	@InitBinder("venda")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(vendaValidator);
	}
	
	@GetMapping("/nova")
	public ModelAndView nova(Venda venda) {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");		
		
		setUuid(venda);
		
		mv.addObject("itens", venda.getItens());
		mv.addObject("valorFrete", venda.getValorFrete());
		mv.addObject("valorDesconto", venda.getValorDesconto());
		mv.addObject("valorTotalItens", tabelaItens.getValorTotal(venda.getUuid()));
		
		return mv;
	}	
	
	@PostMapping(value = "/nova", params = "salvar")
	public ModelAndView salvar(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {		
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}		
		
		venda.setUsuario(usuarioSistema.getUsuario());
		
		cadastroVendaService.salvar(venda);
		attributes.addFlashAttribute("mensagem", "Venda salva com sucesso!");
		return new ModelAndView("redirect:/vendas/nova");
	}

		
	@PostMapping(value = "/nova", params = "emitir")
	public ModelAndView emitir(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {		
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}		
		
		venda.setUsuario(usuarioSistema.getUsuario());
		
		cadastroVendaService.emitir(venda);
		attributes.addFlashAttribute("mensagem", "Venda emitida com sucesso!");
		return new ModelAndView("redirect:/vendas/nova");
	}
	
	@PostMapping(value = "/nova", params = "enviarEmail")
	public ModelAndView enviarEmail(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {		
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}		
		
		venda.setUsuario(usuarioSistema.getUsuario());
		
		venda = cadastroVendaService.salvar(venda);
		mailer.enviar(venda);
				
		attributes.addFlashAttribute("mensagem", String.format("Matricula de nº %d salva com sucesso e e-mail enviado!", venda.getCodigo()));
		return new ModelAndView("redirect:/vendas/nova");
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
	
	@GetMapping
	public ModelAndView pesquisar(VendaFilter vendaFilter,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/venda/PesquisaVendas");
		mv.addObject("todosStatus", StatusVenda.values());
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		PageWrapper<Venda> paginaWrapper = new PageWrapper<>(vendas.filtrar(vendaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Venda venda = vendas.buscarComItens(codigo);
		
		setUuid(venda);
		for (ItemVenda item : venda.getItens()) {
			tabelaItens.adicionarItem(venda.getUuid(), item.getCurso(), item.getQuantidade());
		}
		
		ModelAndView mv = nova(venda);
		mv.addObject(venda);
		return mv;
	}
	
	@PostMapping(value = "/nova", params = "cancelar")
	public ModelAndView cancelar(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		
		try {
			cadastroVendaService.cancelar(venda);
		} catch (AccessDeniedException e) {
			return new ModelAndView("/403");
		}
		
		attributes.addFlashAttribute("mensagem", "Venda cancelada com sucesso");
		return new ModelAndView("redirect:/vendas/" + venda.getCodigo());
	
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
	
	private void validarVenda(Venda venda, BindingResult result) {
		venda.adicionarItens(tabelaItens.getItens(venda.getUuid()));
		venda.calcularValorTotal();
		
		vendaValidator.validate(venda, result);
	}
	
	private void setUuid(Venda venda) {
		if(StringUtils.isEmpty(venda.getUuid())) {
			venda.setUuid(UUID.randomUUID().toString());
		}
	}

}
