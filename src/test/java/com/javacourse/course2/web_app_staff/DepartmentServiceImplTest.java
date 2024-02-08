package com.javacourse.course2.web_app_staff;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javacourse.course2.web_app_staff.model.Department;
import com.javacourse.course2.web_app_staff.model.Employee;
import com.javacourse.course2.web_app_staff.model.Project;
import com.javacourse.course2.web_app_staff.repository.DepartmentRepository;
import com.javacourse.course2.web_app_staff.repository.EmployeeRepository;
import com.javacourse.course2.web_app_staff.repository.ProjectRepository;
import com.javacourse.course2.web_app_staff.service.DepartmentService;
import com.javacourse.course2.web_app_staff.service.dto.DepartmentDto;
import com.javacourse.course2.web_app_staff.service.impl.DepartmentServiceImpl;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
	@Mock
	DepartmentRepository departmentRepository;
	@Mock
	EmployeeRepository employeeRepository;
	@Mock
	ProjectRepository projectRepository;
	@InjectMocks
	DepartmentService departmentService = new DepartmentServiceImpl();
	private UUID randomUUID = UUID.randomUUID();
	private String name = "DepartmentName";
	private Department value = new Department(randomUUID, name);

	@Test
	void find_by_id() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(UUID.randomUUID(), "Employee1"));
		employees.add(new Employee(UUID.randomUUID(), "Employee2"));
		value.setEmployees(employees);
		when(departmentRepository.findById(randomUUID)).thenReturn(value);
		when(employeeRepository.getAll(randomUUID)).thenReturn(employees);
		when(projectRepository.getAll(employees.get(0).getId())).thenReturn(new ArrayList<Project>());
		DepartmentDto departmentExpected = departmentService.findById(randomUUID);
		verify(departmentRepository).findById(randomUUID);

		assertEquals(value.getId(), departmentExpected.getId());
		assertEquals(value.getName(), departmentExpected.getName());
		assertEquals(value.getEmployees(), departmentExpected.getEmployees());
	}

	@Test
	void update() {
		when(departmentRepository.update(value)).thenReturn(value);
		DepartmentDto expected = new DepartmentDto();
		expected.setId(value.getId());
		expected.setName(value.getName());
		departmentService.update(expected);
		verify(departmentRepository).update(value);

		assertEquals(value.getId(), expected.getId());
		assertEquals(value.getName(), expected.getName());
	}

	@Test
	void delete() {
		when(departmentRepository.deleteById(randomUUID)).thenReturn(true);
		boolean isDeleted = departmentService.delete(randomUUID);
		verify(departmentRepository).deleteById(randomUUID);
		
		assertTrue(isDeleted);
	}

}