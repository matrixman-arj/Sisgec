Sisgec = Sisgec || {};

Sisgec.PesquisaRapidaAluno = (function(){
	
	function PesquisaRapidaAluno(){
		this.pesquisaRapidaAlunosModal = $('#pesquisaRapidaAlunos');
		this.nomeInput = $('#nomeAlunoModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-alunos-btn');
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaAlunos');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-aluno').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaAluno.prototype.iniciar = function(){
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		
	}	
		
		function onPesquisaRapidaClicado(event){
			event.preventDefault();
			
			$.ajax({
				url: this.pesquisaRapidaAlunosModal.find('form').attr('action'),
				method: 'GET',
				contentType: 'application/json',
				data:{
					nome: this.nomeInput.val()
				},
				success: onPesquisaConcluida.bind(this),
				error: onErroPesquisa.bind(this)
			});		
	}
	
	function onPesquisaConcluida(resultado){
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		this.mensagemErro.addClass('hidden');
	}
	
	function onErroPesquisa(){
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaAluno;
	
}());

$(function() {
	var pesquisaRapidaAluno = new Sisgec.PesquisaRapidaAluno();
	pesquisaRapidaAluno.iniciar();
});