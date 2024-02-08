package com.javacourse.course2.web_app_staff.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.javacourse.course2.web_app_staff.db.ConnectionManager;
import com.javacourse.course2.web_app_staff.model.Employee;
import com.javacourse.course2.web_app_staff.repository.EmployeeRepository;

public class EmployeeRepositoryImpl implements EmployeeRepository {
	private static final String ID = "id";
	private static final String NAME = "name";

	private static final String GET_EMPLOYEE = "SELECT * FROM employee WHERE id=?";
	private static final String GET_EMPLOYEE_BY_DEPARTMENT_ID = "SELECT * FROM employee WHERE department_id=?";
	private static final String ADD_EMPLOYEE = "INSERT INTO employee(name) VALUES (?)";
	private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
	private static final String UPDATE_EMPLOYEE = "UPDATE employee SET name = ? WHERE id = ?";

	@Override
	public Employee save(Employee t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findById(UUID id) {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pst = con.prepareStatement(GET_EMPLOYEE)) {
			pst.setObject(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				Employee employee = new Employee();
				while (rs.next()) {
					employee.setId(UUID.fromString(rs.getString(ID)));
					employee.setName(rs.getString(NAME));
				}
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteById(UUID id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee update(Employee t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAll(UUID departmentId) {
		List<Employee> employees = new ArrayList<>();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(GET_EMPLOYEE_BY_DEPARTMENT_ID)) {
			pst.setObject(1, departmentId);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Employee employee = new Employee();
					employee.setId(UUID.fromString(rs.getString(ID)));
					employee.setName(rs.getString(NAME));
					employees.add(employee);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

}
