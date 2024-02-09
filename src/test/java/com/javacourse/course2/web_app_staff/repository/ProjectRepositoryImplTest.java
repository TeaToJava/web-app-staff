package com.javacourse.course2.web_app_staff.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.javacourse.course2.web_app_staff.model.Employee;
import com.javacourse.course2.web_app_staff.model.Project;
import com.javacourse.course2.web_app_staff.repository.impl.EmployeeRepositoryImpl;
import com.javacourse.course2.web_app_staff.repository.impl.ProjectRepositoryImpl;

@ExtendWith(DBExtension.class)
class ProjectRepositoryImplTest {

	private ProjectRepository projectRepository = new ProjectRepositoryImpl();
	private EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

	@Test
	void save_project() {
		Project project = new Project("Online shop", "Simple web app");
		Project expectedProject = projectRepository.save(project);
		assertEquals(project.getName(), expectedProject.getName());
		assertEquals(project.getDescription(), expectedProject.getDescription());
	}

	@Test
	void find_project_by_existed_id() {
		Project project = new Project("Education project", "Simple web app");
		UUID uuid = projectRepository.save(project).getId();
		Project expectedProject = projectRepository.findById(uuid);
		assertEquals(uuid, expectedProject.getId());
		assertEquals(project.getName(), expectedProject.getName());
		assertEquals(project.getDescription(), expectedProject.getDescription());
	}

	@Test
	void update_project() {
		Project project = new Project("Education project 2", "Simple web app");
		Project newProject = projectRepository.save(project);
		String name = "Education project 2.1";
		String description = "New version of education project";
		newProject.setName(name);
		newProject.setDescription(description);
		newProject = projectRepository.update(newProject);
		assertEquals(newProject.getName(), name);
		assertEquals(newProject.getDescription(), description);
	}

	@Test
	void delete_project() {
		Project project = new Project("Education project 3", "Simple web app");
		project = projectRepository.save(project);
		boolean isDeleted = projectRepository.deleteById(project.getId());
		assertTrue(isDeleted);
	}
	
	@Test
	void get_all_project_by_user_id() {
		Employee employee = employeeRepository.save(new Employee("Employee " + new Random().nextInt(45)));
		List<Project> projects = projectRepository.getAll(employee.getId());
		assertTrue(projects.isEmpty());
	}	
}