package com.javacourse.course2.web_app_staff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javacourse.course2.web_app_staff.model.Project;
import com.javacourse.course2.web_app_staff.repository.ProjectRepository;
import com.javacourse.course2.web_app_staff.service.ProjectService;
import com.javacourse.course2.web_app_staff.service.dto.ProjectDto;
import com.javacourse.course2.web_app_staff.service.impl.ProjectServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {
	@Mock
	ProjectRepository repository;
	@InjectMocks
	ProjectService projectService = new ProjectServiceImpl();
	private UUID randomUUID = UUID.randomUUID();
	private String name = "ProjectName";
	private String description = "Simple project";
	private Project project = new Project(randomUUID, name, description);

	@Test
	void find_by_id() {
		when(repository.findById(randomUUID)).thenReturn(project);
		ProjectDto projectExpected = projectService.findById(randomUUID);
		verify(repository).findById(randomUUID);

		assertEquals(project.getId(), projectExpected.getId());
		assertEquals(project.getName(), projectExpected.getName());
		assertEquals(project.getDescription(), projectExpected.getDescription());
	}

}
