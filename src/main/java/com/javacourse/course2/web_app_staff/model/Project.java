package com.javacourse.course2.web_app_staff.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Project extends SimpleEntity {

	private UUID projectId;
	private String name;
	private String description;
	private List<Employee> employees = new ArrayList<>();

	public Project() {

	}

	public Project(UUID id, String name, String description) {
		this.projectId = id;
		this.name = name;
		this.description = description;
	}

	@Override
	public UUID getId() {
		return projectId;
	}

	@Override
	public void setId(UUID id) {
		this.projectId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, projectId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(description, other.description) && Objects.equals(projectId, other.projectId)
				&& Objects.equals(name, other.name);
	}

}