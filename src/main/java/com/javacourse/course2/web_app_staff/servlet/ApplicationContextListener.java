package com.javacourse.course2.web_app_staff.servlet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.javacourse.course2.web_app_staff.db.ConnectionManager;
import com.javacourse.course2.web_app_staff.db.DBUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

	private static final String STARTUP_PROPERTIES = "/startup.properties";
	private static final String DB_PROPERTIES = "/db.properties";
	private static final String SQL_CREATE_SCRIPT = "createdb.sql";
	private static final String SQL_INITIALIZE_SCRIPT = "initialize.sql";
	private ClassLoader classLoader = getClass().getClassLoader();

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		HikariConfig hikariConfig = new HikariConfig((classLoader.getResource(STARTUP_PROPERTIES).getPath()));
		ConnectionManager.configDataSource(new HikariDataSource(hikariConfig));
		runDBScript(SQL_CREATE_SCRIPT);
		hikariConfig = new HikariConfig((classLoader.getResource(DB_PROPERTIES).getPath()));
		ConnectionManager.configDataSource(new HikariDataSource(hikariConfig));
		runDBScript(SQL_INITIALIZE_SCRIPT);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		try {
			ConnectionManager.closePool();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void runDBScript(String path) {
		InputStream inpustStream = classLoader.getResourceAsStream(path);
		String sql = new BufferedReader(new InputStreamReader(inpustStream)).lines().collect(Collectors.joining());
		DBUtils.runDBScript(sql);
	}
}
