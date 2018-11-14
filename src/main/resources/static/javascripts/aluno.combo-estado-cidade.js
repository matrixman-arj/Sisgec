var Sisgec = Sisgec || {};

Sisgec.ComboEstado = (function() {
	
	function ComboEstado(){
		this.combo = $('#estado');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);		
	}
	
	ComboEstado.prototype.iniciar = function() {
		this.combo.on('change', onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado() {		
		this.emitter.trigger('alterado', this.combo.val());		
	}
	
	return ComboEstado;
	
}());

Sisgec.ComboCidade = (function() {
	
	function ComboCidade(comboEstado){
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
	}
	
	ComboCidade.prototype.iniciar = function() {
		this.comboEstado.on('alterado', onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado(evento, codigoEstado) {
		var resposta = $.ajax({
			url: this.combo.data('url'),
			method: 'GET',
			contentType: 'application/json',
			data: {'estado': codigoEstado}
		});
	}
	
	return ComboCidade;
	
}());

$(function() {
	
	var comboEstado = new Sisgec.ComboEstado();
	comboEstado.iniciar();
	
	var comboCidade = new Sisgec.ComboCidade(comboEstado);
	comboCidade.iniciar();
	
});