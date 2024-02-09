package com.javacourse.course2.web_app_staff.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import com.javacourse.course2.web_app_staff.service.dto.DepartmentDto;
import com.javacourse.course2.web_app_staff.service.dto.mappers.DepartmentMapper;
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
	private DepartmentMapper departmentMapper = DepartmentMapper.INSTANCE;
	private UUID randomUUID = UUID.randomUUID();
	private String name = "DepartmentName";
	private Department department = new Department(randomUUID, name);

	@Test
	void find_by_id() {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee(UUID.randomUUID(), "Employee1"));
		employees.add(new Employee(UUID.randomUUID(), "Employee2"));
		department.setEmployees(employees);
		when(departmentRepository.findById(randomUUID)).thenReturn(department);
		when(employeeRepository.getAll(randomUUID)).thenReturn(employees);
		when(projectRepository.getAll(employees.get(0).getId())).thenReturn(new ArrayList<Project>());
		DepartmentDto departmentExpected = departmentService.findById(randomUUID);
		verify(departmentRepository).findById(randomUUID);

		assertEquals(department.getId(), departmentExpected.getId());
		assertEquals(department.getName(), departmentExpected.getName());
		assertEquals(department.getEmployees(), departmentExpected.getEmployees());
	}

	@Test
	void find_by_null_id() {
		when(departmentRepository.findById(null)).thenReturn(null);
		DepartmentDto departmentDto = departmentService.findById(null);
		verify(departmentRepository).findById(null);

		assertNull(departmentDto);
	}

	@Test
	void update() {
		when(departmentRepository.update(department)).thenReturn(department);
		DepartmentDto expected = new DepartmentDto();
		expected.setId(department.getId());
		expected.setName(department.getName());
		departmentService.update(expected);
		verify(departmentRepository).update(department);

		assertEquals(department.getId(), expected.getId());
		assertEquals(department.getName(), expected.getName());
	}

	@Test
	void delete() {
		when(departmentRepository.deleteById(randomUUID)).thenReturn(true);
		boolean isDeleted = departmentService.delete(randomUUID);
		verify(departmentRepository).deleteById(randomUUID);

		assertTrue(isDeleted);
	}

	@Test
	void save_department() {
		when(departmentRepository.save(department)).thenReturn(department);
		DepartmentDto departmentExpected = departmentService
				.save(departmentMapper.departmentToDepartmentDto(department));
		verify(departmentRepository).save(department);

		assertEquals(department.getName(), departmentExpected.getName());
	}

}