package com.javacourse.course2.web_app_staff;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.javacourse.course2.web_app_staff.db.ConnectionManager;
import com.javacourse.course2.web_app_staff.db.DBUtils;
import com.javacourse.course2.web_app_staff.model.Department;
import com.javacourse.course2.web_app_staff.repository.DepartmentRepository;
import com.javacourse.course2.web_app_staff.repository.impl.DepartmentRepositoryImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Testcontainers
class DepartmentRepositoryImplTest {

	private static final String DEP_NAME = "Test department";
	private static final String NEW_DEP_NAME = "Test department, new";
	private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
	private Department department = new Department(DEP_NAME);

	private static final String PATH = "src/test/resources/initialize.sql";
	static DataSource datasource;
	static ConnectionManager connectionManager;

	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres").withUsername("postgres")
			.withPassword("test");

	@BeforeAll
	static void beforeAll() {
		HikariConfig config = new HikariConfig();
		JdbcDatabaseContainer<?> jdbcContainer = (JdbcDatabaseContainer<?>) postgres;
		config.setJdbcUrl(jdbcContainer.getJdbcUrl());
		config.setUsername(jdbcContainer.getUsername());
		config.setPassword(jdbcContainer.getPassword());
		config.setDriverClassName(jdbcContainer.getDriverClassName());
		datasource = new HikariDataSource(config);
		ConnectionManager.configDataSource(datasource);
		postgres.start();
		initDB(PATH);
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
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

	@Test
	void save_department() {
		Department expectedDepartment = departmentRepository.save(department);
		assertEquals(department.getName(), expectedDepartment.getName());
	}

	@Test
	void find_by_existed_id() {
		UUID uuid = departmentRepository.save(department).getId();
		Department expectedDepartment = departmentRepository.findById(uuid);
		assertEquals(department.getName(), expectedDepartment.getName());
		assertEquals(uuid, expectedDepartment.getId());
	}

	@Test
	void update_department() {
		Department newDepartment = departmentRepository.save(department);
		newDepartment.setName(NEW_DEP_NAME);
		newDepartment = departmentRepository.update(newDepartment);
		assertEquals(newDepartment.getName(), NEW_DEP_NAME);
	}

	@Test
	void delete_department() {
		department = departmentRepository.save(department);
		boolean isDeleted = departmentRepository.deleteById(department.getId());
		assertTrue(isDeleted);
	}
}