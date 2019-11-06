package com.controller;

//import java.util.ArrayList;
//import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.dao.ClimaDao;
//import com.model.Sensor;

@ManagedBean(name = "climaBean")
@RequestScoped
public class ClimaBean {

	public void getWeather() {
		
		ClimaDao clima = new ClimaDao();
		
		
		//System.out.print(clima);
		//List<Sensor> weatherList = new ArrayList<>();
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
		clima.save();
		
		//return null;
	}

}
