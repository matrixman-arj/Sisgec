Sisgec.TabelaItens = (function() {
	
	function TabelaItens(autocomplete){
		this.autocomplete = autocomplete;
		this.tabelaCursosContainer = $('.js-tabela-cursos-container');
		this.uuid = $('#uuid').val();
		this.emitter = $({});/**2.1 -> Adicionamos o Objeto para disparar o evento e descemos para a função onItemAtualizadoNoServidor*/
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TabelaItens.prototype.iniciar = function(){
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		
		bindQuantidade.call(this);
		bindTabelaItem.call(this);
	}
	
	TabelaItens.prototype.valorTotal = function(){
		return this.tabelaCursosContainer.data('valor');
	}
	
	function onItemSelecionado(evento, item){
		var resposta = $.ajax({
			url:'item',
			method: 'POST',
			data: {
				codigoCurso: item.codigo,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	/**2.0 -> Aqui é onde o item é atualizado no servidor, 
	de onde será disparado um evento, para quem estiver 
	interessado ouvir e realizar alguma ação, que no nosso caso, 
	é o venda.js... Para isso, ainda aqui, vamos até a função TabelaItens  */
	function onItemAtualizadoNoServidor(html){
		this.tabelaCursosContainer.html(html);
		
		bindQuantidade.call(this);
		
		
		var tabelaItem = bindTabelaItem.call(this);
		this.emitter.trigger('tabela-itens-atualizada', tabelaItem.data('valor-total'));/**2.3.8 Agora na hora de mandar o valor total, pegamos o valor total lá do html através do data... Voltamos ao venda.js */
	}
	
	/**##1.0 Início da função que trata a respeito de quando alteramos a quantidade de itens na tabela venda ##*/
	function onQuantidadeItemAlterado(evento){
		var input = $(evento.target);
		var quantidade = input.val();
		
		if (quantidade <= 0){/**1.1 -> Se o usuário digitar um valor menor igual a zero */
			input.val(1); /**1.2 -> Passamos o valor do input para 1 */
			quantidade = 1;/**1.3 -> E setamos o valor da quantidade para 1  */
		}
		
		var codigoCurso = input.data('codigo-curso');
		
		var resposta = $.ajax({
			url:'item/' + codigoCurso,
			method: 'PUT',
			data: {
				quantidade: quantidade,
				uuid: this.uuid
			}
			
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	/**## Fim da função que trata a respeito de quando alteramos a quantidade de itens na tabela venda ##*/
			
	function onDoubleClick(evento){
		var item = $(evento.currentTarget);
		item.toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(evento){
		var codigoCurso = $(evento.target).data('codigo-curso');
		var resposta = $.ajax({
			url: 'item/'+ this.uuid + '/' + codigoCurso,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function bindQuantidade() {
		var quantidadeItemInput = $('.js-tabela-curso-quantidade-item');
		quantidadeItemInput.on('change', onQuantidadeItemAlterado.bind(this));
		quantidadeItemInput.maskMoney({ precision: 0, thousands: '' });/**1.4 -> Se o usuário digitar algo que não seja um número, o campo não aceita */
	}
	
	function bindTabelaItem() {
		/**2.3.6 Onde criamos a variável com nome de tabelaItem... */
		var tabelaItem = $('.js-tabela-item');
		/**2.3.7 Seguido de tabelaItem.on, que recebe 2 clicks para chamar o botão de confirmação de exclusão */
		tabelaItem.on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
		
		/**2.2 -> Quando um item for atualizado no servidor, podemos fazer um trigger falando o seguinte: 
		tabela-itens atualizada, pois quando isso acontecer, teremos o valor total que é a soma de todos 
		os itens no servidor, então podemos passar esse valor total aqui... Mas para descobrirmos que é esse valor total, vamos até o arquivo VendasController */
		
		return tabelaItem;
	}
	
	return TabelaItens;
	
}());