package com.javacourse.course2.web_app_staff.service;

import java.util.UUID;

import com.javacourse.course2.web_app_staff.service.dto.ProjectDto;

public interface ProjectService {
	ProjectDto save(ProjectDto projectDto);

	ProjectDto findById(UUID uuid);

	void update(ProjectDto projectDtO);

	boolean delete(UUID uuid);
}