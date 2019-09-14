package com.cursomarajoara.sisgec.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.cursomarajoara.sisgec.model.Usuario;
import com.cursomarajoara.sisgec.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	public Optional<Usuario> porEmailEAtivo(String email);
		
	public List<String> permissoes(Usuario usuario); 
	
	public List<Usuario> filtrar(UsuarioFilter filtro);



}
