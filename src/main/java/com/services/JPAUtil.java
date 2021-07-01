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
			System.out.print("Conexión establecida...");
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


// package com.acme.ucp;

// import oracle.ucp.jdbc.PoolDataSource;
// import oracle.ucp.jdbc.PoolDataSourceFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;

// import javax.sql.DataSource;
// import java.sql.SQLException;

// @Configuration
// public class DataSourceConfig {
//     @Value("${spring.datasource.url}")
//     private String url;
//     @Value("${spring.datasource.username}")
//     private String username;
//     @Value("${spring.datasource.password}")
//     private String password;
//     @Value("${oracle.ucp.minPoolSize}")
//     private String minPoolSize;

//     @Value("${oracle.ucp.maxPoolSize}")
//     private String maxPoolSize;

//     @Value("${spring.datasource.driver-class-name:oracle.jdbc.pool.OracleDataSource}")
//     private String driverClassName;

//     @Bean(name = "OracleUniversalConnectionPool")
//     @Primary
//     public DataSource getDataSource() {
//         PoolDataSource pds = null;
//         try {
//             pds = PoolDataSourceFactory.getPoolDataSource();

//             pds.setConnectionFactoryClassName(driverClassName);
//             pds.setURL(url);
//             pds.setUser(username);
//             pds.setPassword(password);
//             pds.setMinPoolSize(Integer.valueOf(minPoolSize));
//             pds.setInitialPoolSize(10);
//             pds.setMaxPoolSize(Integer.valueOf(maxPoolSize));

//         } catch (SQLException ea) {
//             System.err.println("Error connecting to the database: " + ea.getMessage());
//         }

//         return pds;
//     }
// }
