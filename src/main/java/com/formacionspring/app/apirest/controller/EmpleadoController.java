package com.formacionspring.app.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionspring.app.apirest.entity.Empleado;
import com.formacionspring.app.apirest.service.EmpleadoService;



@RestController
@RequestMapping("/api")
public class EmpleadoController {

	@Autowired
	private EmpleadoService servicio;
	
	@GetMapping("/empleados")
	public List<Empleado> empleados(){
		return servicio.findAll();
	}
	
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> mostrarEmpleados(@PathVariable Long id){
		Empleado empleado=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			empleado=servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empleado==null) {
			response.put("mensaje", "El empleado con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

		}
		
		return new ResponseEntity<Empleado>(empleado,HttpStatus.OK);
	}
	
	
	@PutMapping("/empleados/{id}")
	public ResponseEntity<?> updateEmpleado( @RequestBody Empleado empleado, @PathVariable long id) {
		Empleado empleadoActual=servicio.findById(id);
		Map<String,Object> response=new HashMap<>();
		try {
			empleadoActual.setNombre(empleado.getNombre());
			empleadoActual.setDni(empleado.getDni());
			empleadoActual.setSalario(empleado.getSalario());
			empleadoActual.setTelefono(empleado.getTelefono());
			empleadoActual.setCodDepartamento(empleado.getCodDepartamento());

			servicio.save(empleadoActual);
		
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar modificacion en el empleado ");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("jefe", empleadoActual);
		response.put("mensaje", "El empleado ha sido modificado  con exito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable Long id) {
		Empleado empleadoBorrado=null;
		
		Map<String,Object> response=new HashMap<>();
				
		empleadoBorrado=servicio.findById(id);
			if(empleadoBorrado==null) {
				response.put("mensaje", "El empleado con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);

			}else {
				try {
					servicio.delete(id);
					
				} catch (DataAccessException e) {
					response.put("mensaje", "Error al borrar el empleado ");
					response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		response.put("mensaje", "El empleado ha sido borrado con exito");
		response.put("jefe", empleadoBorrado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	
	@PostMapping("/empleados")
	public ResponseEntity<?> save(@RequestBody Empleado empleado) {
		Empleado empleadoNew=null;
		Map<String,Object> response=new HashMap<>();
		try {
			empleadoNew=servicio.save(empleado);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar insercion en la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El empleado ha sido creado con exito");
		response.put("empleado", empleadoNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	
}
