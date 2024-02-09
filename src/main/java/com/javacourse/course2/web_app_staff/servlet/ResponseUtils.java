package com.javacourse.course2.web_app_staff.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletResponse;

public class ResponseUtils {

	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private static Gson gson = new Gson();

	private ResponseUtils() {

	}

	public static void respond(HttpServletResponse response, Object objectDto) throws IOException{
		PrintWriter out = response.getWriter();
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding(ENCODING);
		String departmentJsonString = gson.toJson(objectDto);
		out.print(departmentJsonString);
		out.flush();
	}
}