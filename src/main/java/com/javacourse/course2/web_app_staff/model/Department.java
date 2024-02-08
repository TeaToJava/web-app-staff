package com.javacourse.course2.web_app_staff.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Department extends SimpleEntity {

	private UUID departmentId;
	private String name;
	private List<Employee> employees = new ArrayList<>();

	public Department() {

	}

	public Department(UUID id, String name) {
		this.departmentId = id;
		this.name = name;
	}

	public Department(String name) {
		this.name = name;
	}

	@Override
	public UUID getId() {
		return departmentId;
	}

	@Override
	public void setId(UUID id) {
		this.departmentId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return departmentId == other.departmentId && Objects.equals(name, other.name);
	}

}