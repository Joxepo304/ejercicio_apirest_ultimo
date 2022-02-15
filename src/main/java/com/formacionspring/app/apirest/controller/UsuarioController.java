package com.formacionspring.app.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.app.apirest.entity.Empleado;
import com.formacionspring.app.apirest.entity.Usuario;
import com.formacionspring.app.apirest.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private UsuarioService servicio;

	@PostMapping("/login")
	public ResponseEntity<?> loginUsuario(@RequestParam String usuarioEnviado, @RequestParam String password) {
		
		Map<String,Object> response=new HashMap<>();
		boolean login =false;
		boolean usuarioEncontrado = false;
		
		try {
			List<Usuario> listaUsuarios= servicio.findAll();
			
			for (Usuario usuario : listaUsuarios) {
				if(usuarioEnviado.equals(usuario.getUsuario())) {
					usuarioEncontrado=true;
					
					if(password.equals(usuario.getContraseña()) ) {
						login =true;
						break;
					}
				}
			}
			
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insercion en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (login ==true) {
			response.put("Mensaje", "Usuario cargado con exito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
		
		else if (usuarioEncontrado==true && login==false) {
			response.put("Mensaje", "Contraseña incorrecta");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		else if (usuarioEncontrado==false) {
			response.put("Mensaje", "Usuario incorrecto");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
}
