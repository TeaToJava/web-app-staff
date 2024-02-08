package com.javacourse.course2.web_app_staff.service.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.javacourse.course2.web_app_staff.model.Project;

@Mapper
public interface ProjectMapper {
	ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

	ProjectDto projectToProjectDto(Project project);

	Project map(ProjectDto projectDto);
}
