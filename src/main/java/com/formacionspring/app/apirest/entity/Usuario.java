package com.formacionspring.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	@Id
	@Column(unique=true)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	
	@Column(nullable=false,unique=true)
	private String usuario;
	@Column(nullable=false,unique=true)
	private String contraseña;
	
	
	public String getUsuario() {
		return usuario;
	}




	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}




	public String getContraseña() {
		return contraseña;
	}




	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
}
