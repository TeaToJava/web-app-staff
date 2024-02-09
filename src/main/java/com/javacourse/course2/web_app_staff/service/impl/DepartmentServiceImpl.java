package com.javacourse.course2.web_app_staff.service.impl;

import java.util.List;
import java.util.UUID;

import com.javacourse.course2.web_app_staff.model.Department;
import com.javacourse.course2.web_app_staff.model.Employee;
import com.javacourse.course2.web_app_staff.repository.DepartmentRepository;
import com.javacourse.course2.web_app_staff.repository.EmployeeRepository;
import com.javacourse.course2.web_app_staff.repository.ProjectRepository;
import com.javacourse.course2.web_app_staff.repository.impl.DepartmentRepositoryImpl;
import com.javacourse.course2.web_app_staff.repository.impl.EmployeeRepositoryImpl;
import com.javacourse.course2.web_app_staff.repository.impl.ProjectRepositoryImpl;
import com.javacourse.course2.web_app_staff.service.DepartmentService;
import com.javacourse.course2.web_app_staff.service.dto.DepartmentDto;
import com.javacourse.course2.web_app_staff.service.dto.mappers.DepartmentMapper;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
	private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
	private ProjectRepository projectRepository = new ProjectRepositoryImpl();
	private DepartmentMapper departmentMapper = DepartmentMapper.INSTANCE;

	@Override
	public DepartmentDto save(DepartmentDto departmentDto) {
		Department department = departmentRepository.save(departmentMapper.map(departmentDto));
		return departmentMapper.departmentToDepartmentDto(department);
	}

	@Override
	public DepartmentDto findById(UUID uuid) {
		Department department = departmentRepository.findById(uuid);
		if (department != null) {
			List<Employee> employees = employeeRepository.getAll(uuid);
			employees.stream().forEach(e -> e.setProjects(projectRepository.getAll(e.getId())));
			department.setEmployees(employees);
			return departmentMapper.departmentToDepartmentDto(department);
		}
		return null;
	}

	@Override
	public void update(DepartmentDto departmentDto) {
		Department department = departmentMapper.map(departmentDto);
		departmentRepository.update(department);
	}

	@Override
	public boolean delete(UUID uuid) {
		return departmentRepository.deleteById(uuid);
	}

}