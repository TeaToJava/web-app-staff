package com.javacourse.course2.web_app_staff.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ConnectionManager {

	private static DataSource ds;

	private ConnectionManager() {

	}

	public static void configDataSource(DataSource dataSource) {
		ds = dataSource;
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void closePool() {
		try {
			((AutoCloseable) ds).close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
