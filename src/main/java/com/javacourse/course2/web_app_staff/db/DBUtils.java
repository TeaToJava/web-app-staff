package com.javacourse.course2.web_app_staff.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	private DBUtils() {

	}

	public static void runDBScript(String sql) {
		try (Connection db = ConnectionManager.getConnection(); Statement stmt = db.createStatement();) {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}