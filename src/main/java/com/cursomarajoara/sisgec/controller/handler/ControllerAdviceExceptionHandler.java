package com.cursomarajoara.sisgec.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cursomarajoara.sisgec.exception.NomeTipoCursoJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(NomeTipoCursoJaCadastradoException.class)
	public ResponseEntity<String> handleNomeTipoCursoJaCadastradoException(NomeTipoCursoJaCadastradoException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
		
	}

}
