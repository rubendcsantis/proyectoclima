package com.controller;

import javax.persistence.EntityManager;

import com.model.Sensor;
import com.services.JPAUtil;

public class Test {

	public static void main(String[] args) {

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

		Sensor clima = new Sensor();

		clima.setTemperature("32°");
		clima.setHumidity("45%");
		clima.setCity("Prueba 2");

		entity.getTransaction().begin();
		entity.persist(clima);
		entity.getTransaction().commit();

	}

}
