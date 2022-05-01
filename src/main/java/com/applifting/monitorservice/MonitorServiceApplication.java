package com.applifting.monitorservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Properties;


@SpringBootApplication
public class MonitorServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MonitorServiceApplication.class).properties(props())
				.build()
				.run(args);

	}

	private static Properties props() {
		Properties properties = new Properties();
		properties.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/appliftingdb?autoReconnect=true&useSSL=false");
		properties.setProperty("spring.datasource.password", "root");
		properties.setProperty("spring.datasource.username", "root");
		properties.setProperty("spring.jpa.hibernate.ddl-auto", "update");
		properties.setProperty("spring.jpa.show-sql", "true");
		properties.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		properties.setProperty("spring.jpa.properties.hibernate.format_sql", "true");

		return properties;
	}
}
