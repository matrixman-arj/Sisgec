package com.cursomarajoara.sisgec.repository.helper.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cursomarajoara.sisgec.model.Usuario;
import com.cursomarajoara.sisgec.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);



}
