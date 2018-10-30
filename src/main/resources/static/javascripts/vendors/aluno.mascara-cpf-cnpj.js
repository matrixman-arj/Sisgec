var Sisgec = sisgec || {};

Sisgec.MascaraCpfCnpj = (function() {
	
	function MascaraCpfCnpj() {
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		
	}
	
	MascaraCpfCnpj.prototype.iniciar = function() {
		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
	}
	
	function onTipoPessoaAlterado(evento){
		var tipoPessoaSelecionada = $(evento.currentTarget);
		console.log('documento', tipoPessoaSelecionada.data('documento'));
	}
	
	return MascaraCpfCnpj;
	
}());

$(function() {
	var mascaraCpfCnpj = new Sisgec.MascaraCpfCnpj();
	mascaraCpfCnpj.iniciar();
	
});