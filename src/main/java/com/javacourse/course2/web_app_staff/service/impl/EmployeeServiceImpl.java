package com.javacourse.course2.web_app_staff.service.impl;

import com.javacourse.course2.web_app_staff.model.Department;
import com.javacourse.course2.web_app_staff.model.Employee;
import com.javacourse.course2.web_app_staff.repository.DepartmentRepository;
import com.javacourse.course2.web_app_staff.repository.EmployeeRepository;
import com.javacourse.course2.web_app_staff.repository.impl.DepartmentRepositoryImpl;
import com.javacourse.course2.web_app_staff.repository.impl.EmployeeRepositoryImpl;
import com.javacourse.course2.web_app_staff.service.EmployeeService;
import com.javacourse.course2.web_app_staff.service.dto.EmployeeDto;

import com.javacourse.course2.web_app_staff.service.dto.mappers.EmployeeMapper;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
	private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
	private EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

	@Override
	public EmployeeDto save(EmployeeDto employeeDto) {
		Employee employeeFromDto = employeeMapper.map(employeeDto);
		Employee employee = employeeRepository.save(employeeFromDto);
		Department employeeDepartment = employeeFromDto.getDepartment();
		if (employeeDepartment != null) {
			Department department = departmentRepository.findById(employeeDepartment.getId());
			employee.setDepartment(department);
		}
		return employeeMapper.employeeToEmployeeDto(employee);
	}

}