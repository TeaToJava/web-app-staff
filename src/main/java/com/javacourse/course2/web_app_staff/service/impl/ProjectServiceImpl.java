package com.javacourse.course2.web_app_staff.service.impl;

import java.util.UUID;

import com.javacourse.course2.web_app_staff.model.Project;
import com.javacourse.course2.web_app_staff.repository.ProjectRepository;
import com.javacourse.course2.web_app_staff.repository.impl.ProjectRepositoryImpl;
import com.javacourse.course2.web_app_staff.service.ProjectService;
import com.javacourse.course2.web_app_staff.service.dto.ProjectDto;
import com.javacourse.course2.web_app_staff.service.dto.ProjectMapper;

public class ProjectServiceImpl implements ProjectService {

	private ProjectRepository projectRepository = new ProjectRepositoryImpl();
	private ProjectMapper projectMapper = ProjectMapper.INSTANCE;

	@Override
	public ProjectDto findById(UUID uuid) {
		Project project = projectRepository.findById(uuid);
		return projectMapper.projectToProjectDto(project);
	}

	@Override
	public ProjectDto save(ProjectDto projectDto) {
		Project project = projectRepository.save(projectMapper.map(projectDto));
		return projectMapper.projectToProjectDto(project);
	}

	@Override
	public void update(ProjectDto projectDto) {
		Project project = projectMapper.map(projectDto);
		projectRepository.update(project);
	}

	@Override
	public boolean delete(UUID uuid) {
		return projectRepository.deleteById(uuid);
	}

}