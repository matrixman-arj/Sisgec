Sisgec.Venda = (function() {
	
	function Venda(tabelaItens) {
		this.tabelaItens = tabelaItens;
		this.valorTotalBox = $('.js-valor-total-box');
		this.valorFreteInput = $('#valorFrete');
		this.valorDescontoInput = $('#valorDesconto');
		this.valorTotalBoxContainer = $('.js-valor-total-box-container');
		
		this.valorTotalItens = this.tabelaItens.valorTotal();
		this.valorFrete = this.valorFreteInput.data('valor');
		this.valorDesconto = this.valorDescontoInput.data('valor');
	}
	
	Venda.prototype.iniciar = function() {
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
		this.valorFreteInput.on('keyup', onValorFreteAlterado.bind(this));
		this.valorDescontoInput.on('keyup', onValorDescontoAlterado.bind(this));
		
		this.tabelaItens.on('tabela-itens-atualizada', onValoresAlterados.bind(this));		
		this.valorFreteInput.on('keyup', onValoresAlterados.bind(this));
		this.valorDescontoInput.on('keyup', onValoresAlterados.bind(this));
		
		onValoresAlterados.call(this);
	}
	
	function onTabelaItensAtualizada(evento, valorTotalItens) {
		this.valorTotalItens = valorTotalItens == null ? 0 : valorTotalItens;
		console.log('Valor total dos itens sem frete nem desconto', typeof valorTotalItens);
	}
	
	function onValorFreteAlterado(evento) {
		this.valorFrete = Sisgec.recuperarValor($(evento.target).val());
		console.log('Valor do frete COM FORMATAÇÃO:', typeof $(evento.target).val());
		console.log('Valor do frete SEM FORMATAÇÃO:',typeof Sisgec.recuperarValor($(evento.target).val()));
	}
	
	function onValorDescontoAlterado(evento) {
		this.valorDesconto = Sisgec.recuperarValor($(evento.target).val());
		console.log('Valor do Desconto COM FORMATAÇÃO:', typeof $(evento.target).val());
		console.log('Valor do desconto SEM FORMATAÇÃO:', typeof Sisgec.recuperarValor($(evento.target).val()));
		
		
	}
	
	function onValoresAlterados() {
		var valorTotal = numeral(this.valorTotalItens) + numeral(this.valorFrete) - numeral(this.valorDesconto);
		this.valorTotalBox.html(Sisgec.formatarMoeda(valorTotal));
		console.log('Valor Total do pedido:', valorTotal);
		
		this.valorTotalBoxContainer.toggleClass('negativo', valorTotal < 0);
		
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