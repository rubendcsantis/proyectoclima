package com.controller;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import com.dao.ClimaDAO;
import com.model.Sensor;
import com.services.RESTEasyClientGet;
import org.json.JSONObject;

@ManagedBean(name = "climaBean")
@RequestScoped
public class ClimaBean {

	private String select;

	/*
	 * Redirecciona a la vista new.xhtml para crear un registro manualmente.
	 */
	public String newRecord() {
		Sensor c = new Sensor();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("data", c);
		return "/faces/new.xhtml";
	}

	/*
	 * Guarda la información del formulario para el registro manual.
	 */
	public String saveRecord(Sensor sensor) {
		// guarda la fecha de registro
		// Date fechaActual= new Date();
		// cliente.setFregistro(new java.sql.Date(fechaActual.getTime()));

		ClimaDAO clima = new ClimaDAO();
		clima.save(sensor);
		return "/faces/index.xhtml";
	}

	/*
	 * Trae los datos para dataTable (Evento inicial)
	 */
	public List<Sensor> getWeather() {
		ClimaDAO clima = new ClimaDAO();
		return clima.getAllRecords();
	}

	/*
	 * Redirecciona a la vista edit.xhtml para actualizar un registro manualmente,
	 * carga los datos en sesión
	 */
	public String editRecord(int id) {
		ClimaDAO clima = new ClimaDAO();
		Sensor c = new Sensor();
		c = clima.search(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("data", c);
		return "/faces/edit.xhtml";
	}

	/*
	 * Actualiza la información del formulario para el registro manual.
	 */
	public String updateRecord(Sensor sensor) {
		// guarda la fecha de actualizacion
		// Date fechaActual= new Date();
		// cliente.setFactualizar(new java.sql.Date(fechaActual.getTime()));

		ClimaDAO clima = new ClimaDAO();
		clima.edit(sensor);
		return "/faces/index.xhtml";
	}

	/*
	 * Solicita al servicio RESTEasyClientGet y guarda los datos retornados por el
	 * API OpenWatherMap
	 */
	public void OpenWeather() {

		Sensor api = new Sensor();
		ClimaDAO clima = new ClimaDAO();
		RESTEasyClientGet rest = new RESTEasyClientGet();

		JSONObject json = rest.getWeather(this.select + ",CO");

		api.setHumidity(json.get("humidity").toString());
		api.setTemperature(json.get("temp").toString());
		api.setCity(this.select);

		clima.save(api);

	}

	/*
	 * Obtiene la ciudad seleccionada
	 */
	public String getSelect() {
		return select;
	}

	/*
	 * Reserva la ciudad seleccionada
	 */
	public void setSelect(String select) {
		this.select = select;
	}

}
