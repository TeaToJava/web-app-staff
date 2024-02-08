package com.javacourse.course2.web_app_staff.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import com.google.gson.Gson;
import com.javacourse.course2.web_app_staff.service.DepartmentService;
import com.javacourse.course2.web_app_staff.service.dto.DepartmentDto;
import com.javacourse.course2.web_app_staff.service.impl.DepartmentServiceImpl;

@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ID = "id";
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private DepartmentService departmentService = new DepartmentServiceImpl();
	private Gson gson = new Gson();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter(ID);
		if (id != null) {
			UUID uuid = UUID.fromString(id);
			DepartmentDto departmentDto = departmentService.findById(uuid);
			PrintWriter out = response.getWriter();
			response.setContentType(CONTENT_TYPE);
			response.setCharacterEncoding(ENCODING);
			String departmentJsonString = gson.toJson(departmentDto);
			out.print(departmentJsonString);
			out.flush();
		}
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter(ID);
		if (id != null) {
			UUID uuid = UUID.fromString(request.getParameter(ID));
			departmentService.delete(uuid);
		}
	}

}
