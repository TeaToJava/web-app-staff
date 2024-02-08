package com.javacourse.course2.web_app_staff.repository;

import java.util.List;
import java.util.UUID;

import com.javacourse.course2.web_app_staff.model.Project;

public interface ProjectRepository extends SimpleRepository<Project, UUID> {

	List<Project> getAll(UUID userId);

}