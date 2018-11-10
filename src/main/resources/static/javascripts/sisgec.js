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

$(function() {
	var maskMoney = new Sisgec.MaskMoney();
	maskMoney.enable();
	
	var maskPhoneNumber = new Sisgec.MaskPhoneNumber();
	maskPhoneNumber.enable();
	
	var maskCepNumber = new Sisgec.MaskCepNumber();
	maskCepNumber.enable();
}); 