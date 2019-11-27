package com.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.model.Sensor;
import com.services.JPAUtil;

public class ClimaDAO {

	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	/*
	 * Guarda el objeto con sus atributos en BD
	 */
	public void save(Sensor sensor) {
		entity.getTransaction().begin();
		entity.persist(sensor);
		entity.getTransaction().commit();
		// Cierre de conexión
		// JPAUtil.shutdown();
	}

	/*
	 * Actualiza el objeto con sus atributos en BD
	 */
	public void edit(Sensor sensor) {
		entity.getTransaction().begin();
		entity.merge(sensor);
		entity.getTransaction().commit();
		// Cierre de conexión
		/// JPAUtil.shutdown();
	}

	/*
	 * Busca un registro por su ID (retorna un objeto de tipo sensor)
	 */
	public Sensor search(int id) {
		Sensor c = new Sensor();
		c = entity.find(Sensor.class, id);
		// Cierre de conexión
		// JPAUtil.shutdown();
		return c;
	}

	/*
	 * Retorna una lista con todos los registros en BD
	 */
	public List<Sensor> getAllRecords() {
		List<Sensor> queryList = new ArrayList<>();
		Query q = entity.createQuery("SELECT c FROM Sensor c");
		queryList = q.getResultList();
		return queryList;
	}

}
