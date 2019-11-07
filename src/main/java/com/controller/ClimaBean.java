package com.controller;

//import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.dao.ClimaDAO;
import com.model.Sensor;
import com.services.RESTEasyClientGet;
import org.json.JSONObject;

@ManagedBean (name = "climaBean")
@RequestScoped
public class ClimaBean {
	
	private String select;
	
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
	
	public void OpenWeather() {
		
		Sensor api = new Sensor();
		ClimaDAO clima = new ClimaDAO();
		RESTEasyClientGet rest = new RESTEasyClientGet();
		
		JSONObject json = rest.getWeather(this.select + ",CO");
		
		//System.out.print(this.select);
		api.setHumidity(json.get("humidity").toString());
		api.setTemperature(json.get("temp").toString());
		api.setCity(this.select);
		
		clima.guardar(api);
		
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

}
