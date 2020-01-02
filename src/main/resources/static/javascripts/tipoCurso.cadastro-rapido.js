var Sisgec = Sisgec || {};

Sisgec.TipoCursoCadastroRapido = (function(){
	
	function TipoCursoCadastroRapido() {
		 this.modal = $('#modalCadastroRapidoTipoCurso');
		 this.botaoSalvar = this.modal.find('.js-modal-cadastro-tipoCurso-salvar-btn');
		 this.form = this.modal.find('form');
		 this.url = this.form.attr('action');
		 this.inputNomeTipo = $('#nomeTipo');
		 this.inputDescricaoTipo = $('#descricaoTipo');
		 this.containerMensagemErro = $(".js-mensagem-cadastro-rapido-tipoCurso");
		
		
	}
	
	TipoCursoCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });	
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
		
	}
	
	function onModalShow() {
		this.inputNomeTipo.focus();
	}	
	
	function onModalClose() {
		this.inputNomeTipo.val('');
		this.inputDescricaoTipo.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeTipo = this.inputNomeTipo.val().trim();
		var descricaoTipo = this.inputDescricaoTipo.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',			
			data: JSON.stringify({ nome: nomeTipo, descricao: descricaoTipo }),			
			error: onErroSalvandoTipo.bind(this),
			success: onTipoCursoSalvo.bind(this)
		});
	}
	
	function onErroSalvandoTipo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');		
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onTipoCursoSalvo(tipoCurso) {
		var comboTipoCurso = $('#tipoCurso');
		comboTipoCurso.append('<option value=' + tipoCurso.codigo + '>' + tipoCurso.nome + '</option>');
		comboTipoCurso.val(tipoCurso.codigo);
		this.modal.modal('hide');
		
	}
	
	return TipoCursoCadastroRapido;	
	
}());

$(function() {
	
	var tipoCursoCadastroRapido = new Sisgec.TipoCursoCadastroRapido();
	tipoCursoCadastroRapido.iniciar();
});