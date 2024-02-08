package com.javacourse.course2.web_app_staff.model;

import java.util.UUID;

public class EmployeeProject {

	private UUID employeeId;
	private UUID projectId;

	public EmployeeProject(UUID employeeId, UUID projectId) {
		this.employeeId = employeeId;
		this.projectId = projectId;
	}

	public UUID getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}

	public UUID getProjectId() {
		return projectId;
	}

	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}

}