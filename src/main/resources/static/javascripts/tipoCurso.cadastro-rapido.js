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
//		this.modal.on('hide.bs.modal', onModalClose);
//		this.botaoSalvar.on('click', onBotaoSalvarClick);
		
	}
	
	function onModalShow() {
		this.inputNomeTipo.focus();
	}
	
	return TipoCursoCadastroRapido;
	
	
}());

$(function() {
	
	var tipoCursoCadastroRapido = new Sisgec.TipoCursoCadastroRapido();
	tipoCursoCadastroRapido.iniciar();
	
	
//	function onModalClose() {
//		inputNomeTipo.val('');
//		inputDescricaoTipo.val('');
//		containerMensagemErro.addClass('hidden');
//		form.find('.form-group').removeClass('has-error');
//	}
//	
//	function onBotaoSalvarClick() {
//		var nomeTipo = inputNomeTipo.val().trim();
//		var descricaoTipo = inputDescricaoTipo.val().trim();
//		$.ajax({
//			url: url,
//			method: 'POST',
//			contentType: 'application/json',			
//			data: JSON.stringify({ nome: nomeTipo, descricao: descricaoTipo }),			
//			error: onErroSalvandoTipo,
//			success: onTipoCursoSalvo
//		});
//	}
//	
//	function onErroSalvandoTipo(obj) {
//		var mensagemErro = obj.responseText;
//		containerMensagemErro.removeClass('hidden');
//		containerMensagemErro.html('<span>' + mensagemErro + '</span>');		
//		form.find('.form-group').addClass('has-error');
//	}
//	
//	function onTipoCursoSalvo(tipoCurso) {
//		var comboTipoCurso = $('#tipoCurso');
//		comboTipoCurso.append('<option value=' + tipoCurso.codigo_tipoCurso + '>' + tipoCurso.nome + '</option>');
//		comboTipoCurso.val(tipoCurso.codigo_tipoCurso);
//		modal.modal('hide');
//		
//	}
	
});