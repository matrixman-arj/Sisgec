Sisgec.TabelaItens = (function() {
	
	function TabelaItens(autocomplete){
		this.autocomplete = autocomplete;
		this.tabelaCursosContainer = $('.js-tabela-cursos-container');
	}
	
	TabelaItens.prototype.iniciar = function(){
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
	}
	
	function onItemSelecionado(evento, item){
		var resposta = $.ajax({
			url:'item',
			method: 'POST',
			data: {
				codigoCurso: item.codigo
			}
		});
		
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	
	function onItemAdicionadoNoServidor(html){
		this.tabelaCursosContainer.html(html);
		
	}
	
	return TabelaItens;
	
}());

$(function() {
	
	var autocomplete = new Sisgec.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Sisgec.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});