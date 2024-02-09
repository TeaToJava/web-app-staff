package com.javacourse.course2.web_app_staff.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.javacourse.course2.web_app_staff.db.ConnectionManager;
import com.javacourse.course2.web_app_staff.model.Department;
import com.javacourse.course2.web_app_staff.repository.DepartmentRepository;

public class DepartmentRepositoryImpl implements DepartmentRepository {
	private static final String ID = "id";
	private static final String NAME = "name";

	private static final String GET_DEPARTMENT = "SELECT * FROM department WHERE id=?";
	private static final String ADD_DEPARTMENT = "INSERT INTO department(name) VALUES (?)";
	private static final String DELETE_DEPARTMENT = "DELETE FROM department WHERE id = ?";
	private static final String UPDATE_DEPARTMENT = "UPDATE department SET name = ? WHERE id = ?";

	@Override
	public Department findById(UUID id) {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(GET_DEPARTMENT)) {
			pst.setObject(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				Department department = new Department();
				while (rs.next()) {
					department.setId(UUID.fromString(rs.getString(ID)));
					department.setName(rs.getString(NAME));
				}
				return department;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteById(UUID id) {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(DELETE_DEPARTMENT);) {
			pst.setObject(1, id);
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Department save(Department t) {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(ADD_DEPARTMENT, Statement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, t.getName());
			pst.execute();
			try (ResultSet resultSet = pst.getGeneratedKeys()) {
				Department department = new Department();
				if (resultSet.next()) {
					department.setId(UUID.fromString(resultSet.getString(ID)));
					department.setName(resultSet.getString(NAME));
				}
				return department;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Department update(Department t) {
		int placeholder = 1;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(UPDATE_DEPARTMENT);) {
			String name = t.getName();
			UUID id = t.getId();
			pst.setString(placeholder++, name);
			pst.setObject(placeholder, t.getId());
			pst.executeUpdate();
			return new Department(id, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}