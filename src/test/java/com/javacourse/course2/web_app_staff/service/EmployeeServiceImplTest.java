package com.javacourse.course2.web_app_staff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javacourse.course2.web_app_staff.model.Employee;
import com.javacourse.course2.web_app_staff.repository.DepartmentRepository;
import com.javacourse.course2.web_app_staff.repository.EmployeeRepository;
import com.javacourse.course2.web_app_staff.service.dto.EmployeeDto;
import com.javacourse.course2.web_app_staff.service.dto.mappers.EmployeeMapper;
import com.javacourse.course2.web_app_staff.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	EmployeeRepository employeeRepository;
	@Mock
	DepartmentRepository departmentRepository;
	@InjectMocks
	EmployeeService employeeService = new EmployeeServiceImpl();
	private EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;
		
	@Test
	void save_employee() {
		Employee employee = new Employee("Name " + new Random().nextInt(50));	
		when(employeeRepository.save(employee)).thenReturn(employee);
		EmployeeDto expectedEmployee = employeeService.save(employeeMapper.employeeToEmployeeDto(employee));
		verify(employeeRepository).save(employee);

		assertEquals(employee.getName(), expectedEmployee.getName());
	}

}