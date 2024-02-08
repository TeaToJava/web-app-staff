package com.javacourse.course2.web_app_staff;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javacourse.course2.web_app_staff.service.DepartmentService;
import com.javacourse.course2.web_app_staff.service.dto.DepartmentDto;
import com.javacourse.course2.web_app_staff.servlet.DepartmentServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class DepartmentServletTest {
	@Mock
	DepartmentService departmentService;
	@InjectMocks
	DepartmentServlet departmentServlet = new DepartmentServlet();
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	private String ID = "id";
	private UUID randomUUID = UUID.randomUUID();
	private String uuidAsString = randomUUID.toString();
	

	@Test
	void test_get_department() throws ServletException, IOException {
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setId(randomUUID);
		departmentDto.setName("DepartmentName");
		when(request.getParameter(ID)).thenReturn(uuidAsString);
		when(departmentService.findById(randomUUID)).thenReturn(departmentDto);
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);

		departmentServlet.doGet(request, response);
		verify(departmentService).findById(randomUUID);
		String uuid = String.format("\"id\":\"%s\"", uuidAsString);
		String name = String.format("\"name\":\"%s\"", departmentDto.getName());
		assertTrue(stringWriter.toString().contains(uuid));
		assertTrue(stringWriter.toString().contains(name));
	}

	@Test
	void test_delete_department() throws ServletException, IOException {
		when(request.getParameter(ID)).thenReturn(uuidAsString);
		when(departmentService.delete(randomUUID)).thenReturn(true);
		departmentServlet.doDelete(request, response);
		verify(departmentService).delete(randomUUID);
		assertTrue(departmentService.delete(randomUUID));
	}
}