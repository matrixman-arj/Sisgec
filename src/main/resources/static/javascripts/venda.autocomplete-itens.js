Sisgec = Sisgec || {};

Sisgec.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuOuNomeInput = $('.js-sku-nome-curso-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-curso').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
		
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(skuOuNome) { 
				return '/sisgec/cursos?skuOuNome=' + skuOuNome;
			},
			getValue: 'nome',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: template.bind(this)
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			}
		};
		
		this.skuOuNomeInput.easyAutocomplete(options);
	}
	
	function onItemSelecionado() {
		 this.emitter.trigger('item-selecionado', this.skuOuNomeInput.getSelectedItemData());
	}
	
	function template(nome, curso) {
		curso.valorFormatado = Sisgec.formatarMoeda(curso.valor);
		return this.template(curso);
	}
	
	return Autocomplete
	
}());



