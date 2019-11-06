package com.controller;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.dao.ClimaDAO;
import com.model.Sensor;

@ManagedBean (name = "climaBean")
@RequestScoped
public class ClimaBean {
	
	public String nuevo() {
		Sensor c= new Sensor();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("data", c);
		return  "/faces/new.xhtml";
	}
	
	public String guardar (Sensor sensor) {
		//guarda la fecha de registro
		//Date fechaActual= new Date();
		//cliente.setFregistro(new java.sql.Date(fechaActual.getTime()));
		
		ClimaDAO clima= new ClimaDAO();
		clima.guardar(sensor);
		return  "/faces/index.xhtml";
	}

	public List<Sensor> getWeather() {
		
		ClimaDAO clima = new ClimaDAO();
		
		//List<Sensor> weatherList = new ArrayList<>();
		
		
		//System.out.print(clima);
		//Sensor city = new Sensor();
		
		/*city.setCity("Bogotá");
		city.setHumidity("63%");
		city.setTemperature("23°");
		
		Sensor city2 = new Sensor();

		city2.setCity("Prueba");
		city2.setHumidity("43%");
		city2.setTemperature("28°");

		weatherList.add(city);
		weatherList.add(city2);
		System.out.print(weatherList);*/

		//return weatherList;
		//clima.save();
		
		return clima.obtenerClientes();
	}
	
	public String editar(int id) {
		ClimaDAO clima = new ClimaDAO();
		Sensor c = new Sensor();
		c = clima.buscar(id);
		//System.out.println("******************************************");
		//System.out.println(c);

		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("data", c);
		return "/faces/edit.xhtml";
	}

	public String actualizar(Sensor sensor) {
		//guarda la fecha de actualizacion
		//Date fechaActual= new Date();
		//cliente.setFactualizar(new java.sql.Date(fechaActual.getTime()));
		
		ClimaDAO clima = new ClimaDAO();
		clima.editar(sensor);
		return "/faces/index.xhtml";
	}
//
//	// eliminar un cliente
//	public String eliminar(Long id) {
//		ClienteDAO clienteDAO = new ClienteDAO();
//		clienteDAO.eliminar(id);
//		System.out.println("Cliente eliminado..");
//		return "/faces/index.xhtml";
//	}

}
