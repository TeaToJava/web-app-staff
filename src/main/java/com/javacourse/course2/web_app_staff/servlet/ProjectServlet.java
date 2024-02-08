package com.javacourse.course2.web_app_staff.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.javacourse.course2.web_app_staff.service.ProjectService;
import com.javacourse.course2.web_app_staff.service.dto.ProjectDto;
import com.javacourse.course2.web_app_staff.service.impl.ProjectServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/projects")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ID = "id";
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private ProjectService projectService = new ProjectServiceImpl();
	private Gson gson = new Gson();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter(ID);
		if (id != null) {
			UUID uuid = UUID.fromString(id);
			ProjectDto projectDto = projectService.findById(uuid);
			PrintWriter out = response.getWriter();
			response.setContentType(CONTENT_TYPE);
			response.setCharacterEncoding(ENCODING);
			String projectJsonString = gson.toJson(projectDto);
			out.print(projectJsonString);
			out.flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String body = request.getReader().lines().collect(Collectors.joining());
		ProjectDto projectDTO = gson.fromJson(body, ProjectDto.class);
		projectService.update(projectDTO);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter(ID);
		if (id != null) {
			UUID uuid = UUID.fromString(request.getParameter(ID));
			projectService.delete(uuid);
			response.getWriter().append("Project is deleted");
		}
	}

}