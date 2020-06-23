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
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onItemAtualizadoNoServidor(html){
		this.tabelaCursosContainer.html(html);
		$('.js-tabela-curso-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
		$('.js-tabela-item').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
	}
	
	function onQuantidadeItemAlterado(evento){
		var input = $(evento.target);
		var quantidade = input.val();
		var codigoCurso = input.data('codigo-curso');
		
		var resposta = $.ajax({
			url:'item/' + codigoCurso,
			method: 'PUT',
			data: {
				quantidade: quantidade
			}
			
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
			
	function onDoubleClick(evento){
		var item = $(evento.currentTarget);
		item.toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(evento){
		var codigoCurso = $(evento.target).data('codigo-curso');
		var resposta = $.ajax({
			url: 'item/' + codigoCurso,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	return TabelaItens;
	
}());

$(function() {
	
	var autocomplete = new Sisgec.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Sisgec.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});