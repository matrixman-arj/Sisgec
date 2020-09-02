Sisgec.Venda = (function() {
	
	function Venda(tabelaItens) {
		this.tabelaItens = tabelaItens;
		this.valorTotalBox = $('.js-valor-total-box');
		this.valorFreteInput = $('#valorFrete');
		this.valorDescontoInput = $('#valorDesconto');
		
		this.valorTotalItens = 0;
		this.valorFrete = 0;
		this.valorDesconto = 0;
	}
	
	Venda.prototype.iniciar = function() {
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
		this.valorFreteInput.on('keyup', onValorFreteAlterado.bind(this));
		this.valorDescontoInput.on('keyup', onValorDescontoAlterado.bind(this));
		
		this.tabelaItens.on('tabela-itens-atualizada', onValoresAlterados.bind(this));
		this.valorFreteInput.on('keyup', onValoresAlterados.bind(this));
		this.valorDescontoInput.on('keyup', onValoresAlterados.bind(this));
	}
	
	function onTabelaItensAtualizada(evento, valorTotalItens) {
		this.valorTotalItens = valorTotalItens == null ? 0 : valorTotalItens;
		console.log('valorTotalItens', valorTotalItens);
	}
	
	function onValorFreteAlterado(evento) {
		this.valorFrete = Sisgec.recuperarValor($(evento.target).val());
		console.log('valorFrete', valorFrete);
	}
	
	function onValorDescontoAlterado(evento) {
		this.valorDesconto = Sisgec.recuperarValor($(evento.target).val());
		console.log('valorDesconto', valorDesconto);
	}
	
	function onValoresAlterados() {
		var valorTotal = this.valorTotalItens + this.valorFrete - this.valorDesconto;
		this.valorTotalBox.html(Sisgec.formatarMoeda(valorTotal));
		console.log('valorTotal', valorTotal);
	}
	
	return Venda;
	
}());

$(function() {
	
	var autocomplete = new Sisgec.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Sisgec.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var venda = new Sisgec.Venda(tabelaItens);
	venda.iniciar();
	
});