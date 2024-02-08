package com.javacourse.course2.web_app_staff.service.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.javacourse.course2.web_app_staff.model.Department;

@Mapper
public interface DepartmentMapper {

	DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

	DepartmentDto departmentToDepartmentDto(Department department);
	
	Department map(DepartmentDto departmentDto);
}