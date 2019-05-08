var Sisgec = Sisgec || {};

Sisgec.MaskMoney = (function(){
	
	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
		
	}
	
	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney({decimal: ',', thousands: '.'});
		this.plain.maskMoney({ precision: 0, thousands: '.' });		
		
	}
	
	return MaskMoney
	
}());

Sisgec.MaskPhoneNumber = (function(){
	
	function MaskPhoneNumber(){
		this.inputPhoneNumber = $('.js-phone-number');
		
	}
	
	MaskPhoneNumber.prototype.enable = function(){		
		var maskBehavior = function (val){
			return val.replace(/\D/g,'').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		
		var options = {
			onKeyPress: function(val, e, field, options) {
				field.mask(maskBehavior.apply({}, arguments), options);
			}
		};
		
		this.inputPhoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhoneNumber;
	
}());

Sisgec.MaskCepNumber = (function() {
	
	function MaskCepNumber() {
		this.inputCepNumber = $('.js-cep-number');
		
	}
	
	MaskCepNumber.prototype.enable = function() {
		this.inputCepNumber.mask('00.000-000');
	}
	
	return MaskCepNumber;
	
}());

Sisgec.MaskDate = (function(){
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;
	
}());

Sisgec.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings){
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
}());

$(function() {
	var maskMoney = new Sisgec.MaskMoney();
	maskMoney.enable();
	
	var maskPhoneNumber = new Sisgec.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	var maskCepNumber = new Sisgec.MaskCepNumber();
	maskCepNumber.enable();
	
	var maskDate = new Sisgec.MaskDate();
	maskDate.enable();
	
	var security = new Sisgec.Security();
	security.enable();
}); 