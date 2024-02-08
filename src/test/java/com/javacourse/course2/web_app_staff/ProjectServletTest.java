package com.javacourse.course2.web_app_staff;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javacourse.course2.web_app_staff.service.ProjectService;
import com.javacourse.course2.web_app_staff.service.dto.ProjectDto;
import com.javacourse.course2.web_app_staff.servlet.ProjectServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class ProjectServletTest {

	@Mock
	ProjectService projectService;
	@InjectMocks
	ProjectServlet projectServlet = new ProjectServlet();
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	private static final String ID = "id";
	private UUID randomUUID = UUID.randomUUID();
	private String uuidAsString = randomUUID.toString();

	@Test
	void test_get_project() throws ServletException, IOException {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(randomUUID);
		projectDto.setName("Project");
		projectDto.setDescription("Simple project");
		when(request.getParameter(ID)).thenReturn(uuidAsString);
		when(projectService.findById(randomUUID)).thenReturn(projectDto);
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);

		projectServlet.doGet(request, response);
		verify(projectService).findById(randomUUID);
		String uuid = String.format("\"id\":\"%s\"", uuidAsString);
		String name = String.format("\"name\":\"%s\"", projectDto.getName());
		assertTrue(stringWriter.toString().contains(uuid));
		assertTrue(stringWriter.toString().contains(name));
	}

}
