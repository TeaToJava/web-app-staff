package com.javacourse.course2.web_app_staff.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.javacourse.course2.web_app_staff.db.ConnectionManager;
import com.javacourse.course2.web_app_staff.model.Project;
import com.javacourse.course2.web_app_staff.repository.ProjectRepository;

public class ProjectRepositoryImpl implements ProjectRepository {

	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";

	private static final String GET_PROJECT = "SELECT * FROM projects WHERE id=?";
	private static final String GET_PROJECTID_BY_USERID = "SELECT * FROM employee_projects WHERE employee_id=?";
	private static final String ADD_PROJECT = "INSERT INTO projects(name, description) VALUES (?, ?)";
	private static final String DELETE_PROJECT = "DELETE FROM projects WHERE id = ?";
	private static final String UPDATE_PROJECT = "UPDATE projects SET name = ?, description = ? WHERE id = ?";

	@Override
	public Project save(Project t) {
		int placeholder = 1;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(ADD_PROJECT, Statement.RETURN_GENERATED_KEYS);) {
			pst.setString(placeholder++, t.getName());
			pst.setString(placeholder++, t.getDescription());
			pst.execute();
			try (ResultSet resultSet = pst.getGeneratedKeys()) {
				Project project = new Project();
				if (resultSet.next()) {
					project.setId(UUID.fromString(resultSet.getString(ID)));
					project.setName(resultSet.getString(NAME));
					project.setDescription(resultSet.getString(DESCRIPTION));
				}
				return project;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Project findById(UUID id) {
		int placeholder = 1;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(GET_PROJECT)) {
			pst.setObject(placeholder, id);
			try (ResultSet rs = pst.executeQuery()) {
				Project project = new Project();
				while (rs.next()) {
					project.setId(UUID.fromString(rs.getString(ID)));
					project.setName(rs.getString(NAME));
					project.setDescription(rs.getString(DESCRIPTION));
				}
				return project;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteById(UUID id) {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(DELETE_PROJECT);) {
			pst.setObject(1, id);
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Project update(Project t) {
		int placeholder = 1;
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(UPDATE_PROJECT);) {
			String name = t.getName();
			String description = t.getDescription();
			UUID id = t.getId();
			pst.setString(placeholder++, name);
			pst.setString(placeholder++, description);
			pst.setObject(placeholder, t.getId());
			pst.executeUpdate();
			return new Project(id, name, description);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Project> getAll(UUID userId) {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(GET_PROJECTID_BY_USERID)) {
			List<Project> projects = new ArrayList<>();
			List<UUID> projectIds = new ArrayList<>();
			pst.setObject(1, userId);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					projectIds.add(UUID.fromString(rs.getString("project_id")));
				}
			}
			projectIds.stream().forEach(id -> projects.add(findById(id)));
			return projects;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}