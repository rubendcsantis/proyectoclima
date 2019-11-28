package com.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
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
	private List<Sensor> filteredWeather;
	private Date currentDate = new Date();

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
		sensor.setDate(new java.sql.Date(currentDate.getTime()));
		sensor.setTime(new java.sql.Time(currentDate.getTime()));
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
		sensor.setDate(new java.sql.Date(currentDate.getTime()));
		sensor.setTime(new java.sql.Time(currentDate.getTime()));
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
		api.setDate(new java.sql.Date(currentDate.getTime()));
		api.setTime(new java.sql.Time(currentDate.getTime()));

		clima.save(api);
		this.saveMessage();

	}

	/*
	 * Función de mensaje global
	 */
	private void saveMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Buen trabajo", "Carga exitosa."));
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

	/*
	 * Obtiene los filtros seleccionados
	 */
	public List<Sensor> getFilteredWeather() {
		return filteredWeather;
	}

	/*
	 * Reserva los filtros seleccionados
	 */
	public void setFilteredWeather(List<Sensor> filteredWeather) {
		this.filteredWeather = filteredWeather;
	}

}
