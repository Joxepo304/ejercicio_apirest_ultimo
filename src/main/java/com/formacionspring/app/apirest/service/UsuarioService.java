package com.formacionspring.app.apirest.service;

import java.util.List;

import com.formacionspring.app.apirest.entity.Usuario;


public interface UsuarioService {

	
	public List<Usuario> findAll();
	public Usuario findById (Long id);
	public Usuario save (Usuario usuario);
	public void delete (Long id);
	
}
