package com.cursomarajoara.sisgec.service.event.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.cursomarajoara.sisgec.storage.FotoStorage;

@Component
public class CursoListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto() and #evento.novaFoto")
	public void cursoSalvo(CursoSalvoEvent evento) {		
		fotoStorage.salvar(evento.getCurso().getFoto());

		

	}

}
