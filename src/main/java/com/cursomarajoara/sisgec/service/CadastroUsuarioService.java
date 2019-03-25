package com.cursomarajoara.sisgec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomarajoara.sisgec.model.Usuario;
import com.cursomarajoara.sisgec.repository.Usuarios;
import com.cursomarajoara.sisgec.service.exception.EmailUsuarioJaCadastradoException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;
		
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		if(usuarioExistente.isPresent()) {
			throw new EmailUsuarioJaCadastradoException(" Email já cadastrado");
		}
		usuarios.save(usuario);
		
	}
}
