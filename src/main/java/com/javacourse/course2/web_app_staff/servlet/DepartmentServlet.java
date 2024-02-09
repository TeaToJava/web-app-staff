package com.javacourse.course2.web_app_staff.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import com.javacourse.course2.web_app_staff.service.DepartmentService;
import com.javacourse.course2.web_app_staff.service.dto.DepartmentDto;
import com.javacourse.course2.web_app_staff.service.impl.DepartmentServiceImpl;

@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ID = "id";
	private DepartmentService departmentService = new DepartmentServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter(ID);
			if (id != null) {
				UUID uuid = UUID.fromString(id);
				DepartmentDto departmentDto = departmentService.findById(uuid);
				ResponseUtils.respond(response, departmentDto);
			}
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter(ID);
			if (id != null) {
				UUID uuid = UUID.fromString(request.getParameter(ID));
				departmentService.delete(uuid);
			}
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}