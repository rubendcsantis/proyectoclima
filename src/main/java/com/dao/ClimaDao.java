package com.dao;

//import java.util.ArrayList;
//import java.util.List;
import javax.persistence.EntityManager;
//import javax.persistence.Query;
import com.model.Sensor;
import com.services.JPAUtil;

public class ClimaDao {

	//EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	//Sensor clima = new Sensor();
	// EntityManager entity =
	// JPAUtil.getEntityManagerFactory().createEntityManager();

	public void save() {

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

		Sensor clima = new Sensor();

		clima.setTemperature("32°");
		clima.setHumidity("45%");
		clima.setCity("Prueba 2");

		entity.getTransaction().begin();
		entity.persist(clima);
		entity.getTransaction().commit();

	}

	/*
	 * public void save(Sensor sensor) { entity.getTransaction().begin();
	 * entity.persist(sensor); entity.getTransaction().commit(); JPAUtil.shutdown();
	 * }
	 * 
	 * public void edit(Sensor sensor) { entity.getTransaction().begin();
	 * entity.merge(sensor); entity.getTransaction().commit(); JPAUtil.shutdown(); }
	 * 
	 * public Sensor getCityByid(int id) { Sensor ciudad = new Sensor(); ciudad =
	 * entity.find(Sensor.class, id); JPAUtil.shutdown(); return ciudad; }
	 * 
	 * @SuppressWarnings("unchecked") public List<Sensor> getCity() { List<Sensor>
	 * listaDatos = new ArrayList<>(); Query q =
	 * entity.createQuery("SELECT s FROM Sensor s"); listaDatos = q.getResultList();
	 * System.out.print(listaDatos); return listaDatos; }
	 */

}
