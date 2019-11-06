package com.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.model.Sensor;
import com.services.JPAUtil;

public class ClimaDAO {
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	public void guardar(Sensor sensor) {
		entity.getTransaction().begin();
		entity.persist(sensor);
		entity.getTransaction().commit();
		// JPAUtil.shutdown();
	}

	public void editar(Sensor sensor) {
		entity.getTransaction().begin();
		entity.merge(sensor);
		entity.getTransaction().commit();
		/// JPAUtil.shutdown();
	}

	public Sensor buscar(int id) {
		Sensor c = new Sensor();
		c = entity.find(Sensor.class, id);
		// JPAUtil.shutdown();
		return c;
	}

//	public void eliminar(Long id) {
//		Sensor c = new Sensor();
//		c = entity.find(Sensor.class, id);
//		entity.getTransaction().begin();
//		entity.remove(c);
//		entity.getTransaction().commit();
//	}

	public List<Sensor> obtenerClientes() {
		List<Sensor> listaClientes = new ArrayList<>();
		Query q = entity.createQuery("SELECT c FROM Sensor c");
		listaClientes = q.getResultList();
		return listaClientes;
	}

}
