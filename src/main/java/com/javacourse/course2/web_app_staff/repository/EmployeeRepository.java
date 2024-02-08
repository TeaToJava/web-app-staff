package com.javacourse.course2.web_app_staff.repository;

import java.util.List;
import java.util.UUID;

import com.javacourse.course2.web_app_staff.model.Employee;

public interface EmployeeRepository extends SimpleRepository<Employee, UUID> {

	List<Employee> getAll(UUID departmentId);

}