package com.javacourse.course2.web_app_staff.service;

import java.util.UUID;

import com.javacourse.course2.web_app_staff.service.dto.DepartmentDto;

public interface DepartmentService {
	DepartmentDto save(DepartmentDto departmentDto);

	DepartmentDto findById(UUID uuid);

	void update(DepartmentDto departmentDto);

	boolean delete(UUID uuid);
}