package com.services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	private static EntityManagerFactory factory;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		if (factory != null) {
			//System.out.print("Conexión establecida...");
			return factory;
		}
		return null;
	}

	public static void shutdown() {
		if (factory != null) {
			factory.close();
		}
	}
}
