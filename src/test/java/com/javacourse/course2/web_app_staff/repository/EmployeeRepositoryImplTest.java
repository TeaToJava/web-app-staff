package com.javacourse.course2.web_app_staff.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.javacourse.course2.web_app_staff.model.Department;
import com.javacourse.course2.web_app_staff.model.Employee;
import com.javacourse.course2.web_app_staff.repository.impl.DepartmentRepositoryImpl;
import com.javacourse.course2.web_app_staff.repository.impl.EmployeeRepositoryImpl;

@ExtendWith(DBExtension.class)
class EmployeeRepositoryImplTest {

	private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
	private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
	private Employee employee;

	@BeforeEach
	void createDepartment() {
		employee = new Employee("New employee" + new Random().nextInt(500));
	}

	@Test
	void save_employee() {
		Employee expectedEmployee = employeeRepository.save(employee);
		assertEquals(employee.getName(), expectedEmployee.getName());
	}

	@Test
	void find_employee_by_existed_id() {
		UUID uuid = employeeRepository.save(employee).getId();
		Employee expectedEmployee = employeeRepository.findById(uuid);
		assertEquals(employee.getName(), expectedEmployee.getName());
		assertEquals(uuid, expectedEmployee.getId());
	}

	@Test
	void get_all() {
		Random random = new Random();
		Department department = new Department("New department" + random.nextInt(500));
		Department newDepartment = departmentRepository.save(department);
		List<Employee> employees = new ArrayList<Employee>();
		List<Employee> employeesExpected = employeeRepository.getAll(newDepartment.getId());
		assertEquals(employeesExpected, employees);
	}

	@Test
	void update_employee() {
		Employee newEmployee = employeeRepository.save(employee);
		String name = "Edited employee";
		newEmployee.setName(name);
		newEmployee = employeeRepository.update(newEmployee);
		assertEquals(newEmployee.getName(), name);
	}

	@Test
	void delete_employee() {
		employee = employeeRepository.save(employee);
		boolean isDeleted = employeeRepository.deleteById(employee.getId());
		assertTrue(isDeleted);
	}

}