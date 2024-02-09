package com.javacourse.course2.web_app_staff.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Employee extends SimpleEntity {

	private UUID employeeId;
	private String name;
	private Department department;
	private List<Project> projects = new ArrayList<>();

	public Employee() {

	}

	public Employee(String name) {
		super();
		this.name = name;
	}

	public Employee(UUID id, String name) {
		this.employeeId = id;
		this.name = name;
	}

	public Employee(String name, Department department) {
		super();
		this.name = name;
		this.department = department;
	}

	@Override
	public UUID getId() {
		return employeeId;
	}

	@Override
	public void setId(UUID id) {
		this.employeeId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(employeeId, other.employeeId) && Objects.equals(name, other.name);
	}

}