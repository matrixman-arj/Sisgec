var Sisgec = Sisgec || {};

Sisgec.UploadFoto = (function(){
	
	function UploadFoto() {
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');
		
		this.htmlFotoCursoTemplate = $('#foto-curso').html();
		this.template = Handlebars.compile(this.htmlFotoCursoTemplate);		
		
		this.containerFotoCurso = $('.js-container-foto-curso');
		
		this.uploadDrop = $('#upload-drop');
	}
	
	UploadFoto.prototype.iniciar = function () {
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(jpg|jpeg|png)',
				action: this.containerFotoCurso.data('url-fotos'),
				complete: onUploadCompleto.bind(this),
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
	}
	
	function onUploadCompleto(resposta) {
		this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		
		this.uploadDrop.addClass('hidden');
		var htmlFotoCurso = this.template({nomeFoto: resposta.nome});
		this.containerFotoCurso.append(htmlFotoCurso);
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}
	
	function onRemoverFoto() {
		$('.js-foto-curso').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
	}
	
	return UploadFoto;
	
})();

$(function(){
	var uploadFoto = new Sisgec.UploadFoto();
	uploadFoto.iniciar();	
});