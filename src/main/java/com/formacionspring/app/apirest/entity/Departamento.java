package com.formacionspring.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="departamentos")
public class Departamento implements Serializable {

	private String nombre;
	private String ubicacion;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long codDepartamento;
	
	
	
	
	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getUbicacion() {
		return ubicacion;
	}




	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}




	public long getCodDepartamento() {
		return codDepartamento;
	}




	public void setCodDepartamento(long codDepartamento) {
		this.codDepartamento = codDepartamento;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
