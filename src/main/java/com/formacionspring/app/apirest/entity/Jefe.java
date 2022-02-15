package com.formacionspring.app.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="jefes")
public class Jefe implements Serializable{

	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cod_departamento")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Departamento codDepartamento;
	
	
	@Column(nullable=false,unique=true)
	private String dni;
	@Column(nullable=false)
	private String nombre;
	
	private float salario;
	
	private int telefono;
	
	@Id
	@Column(unique=true)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long idDep;
	
	
	
	
	
	public Departamento getCodDepartamento() {
		return codDepartamento;
	}





	public void setCodDepartamento(Departamento codDepartamento) {
		this.codDepartamento = codDepartamento;
	}





	public String getDni() {
		return dni;
	}





	public void setDni(String dni) {
		this.dni = dni;
	}





	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public float getSalario() {
		return salario;
	}





	public void setSalario(float salario) {
		this.salario = salario;
	}





	public int getTelefono() {
		return telefono;
	}





	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}





	public long getIdDep() {
		return idDep;
	}





	public void setIdDep(long idDep) {
		this.idDep = idDep;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
