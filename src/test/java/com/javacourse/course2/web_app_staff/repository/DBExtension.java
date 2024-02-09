package com.javacourse.course2.web_app_staff.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.javacourse.course2.web_app_staff.db.ConnectionManager;
import com.javacourse.course2.web_app_staff.db.DBUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Testcontainers
public class DBExtension implements BeforeAllCallback, AfterAllCallback {

	private static final String PATH = "src/test/resources/initialize.sql";
	static DataSource datasource;
	static ConnectionManager connectionManager;
	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres").withUsername("postgres")
			.withPassword("test");

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		postgres.start();
		HikariConfig config = new HikariConfig();
		JdbcDatabaseContainer<?> jdbcContainer = (JdbcDatabaseContainer<?>) postgres;
		config.setJdbcUrl(jdbcContainer.getJdbcUrl());
		config.setUsername(jdbcContainer.getUsername());
		config.setPassword(jdbcContainer.getPassword());
		config.setDriverClassName(jdbcContainer.getDriverClassName());
		datasource = new HikariDataSource(config);
		ConnectionManager.configDataSource(datasource);
		initDB(PATH);
	}

	private static void initDB(String path) {
		String sql = null;
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
			sql = new String(encoded);
			DBUtils.runDBScript(sql);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		postgres.stop();
		ConnectionManager.closePool();
	}

}