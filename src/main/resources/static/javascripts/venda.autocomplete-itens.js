Sisgec = Sisgec || {};

Sisgec.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuOuNomeInput = $('.js-sku-nome-curso-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-curso').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		
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
				method: function(nome, curso){

					curso.valorFormatado = Sisgec.formatarMoeda(curso.valor);
					return this.template(curso);
				}.bind(this)
			}
		};
		
		this.skuOuNomeInput.easyAutocomplete(options);
	}
	
	return Autocomplete
	
}());

$(function() {
	
	var autocomplete = new Sisgec.Autocomplete();
	autocomplete.iniciar();
	
})

