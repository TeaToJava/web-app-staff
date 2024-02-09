package com.javacourse.course2.web_app_staff.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.javacourse.course2.web_app_staff.model.Department;
import com.javacourse.course2.web_app_staff.repository.impl.DepartmentRepositoryImpl;

@ExtendWith(DBExtension.class)
class DepartmentRepositoryImplTest {

	private static final String DEP_NAME = "Test department";
	private DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
	private Department department;
	
	@BeforeEach
	void createDepartment(){
		department = new Department(DEP_NAME + new Random().nextInt(500));
	}

	@Test
	void save_department() {
		Department expectedDepartment = departmentRepository.save(department);
		assertEquals(department.getName(), expectedDepartment.getName());
	}

	@Test
	void find_by_existed_id() {
		UUID uuid = departmentRepository.save(department).getId();
		Department expectedDepartment = departmentRepository.findById(uuid);
		assertEquals(department.getName(), expectedDepartment.getName());
		assertEquals(uuid, expectedDepartment.getId());
	}

	@Test
	void update_department() {
		Department newDepartment = departmentRepository.save(department);
		String newName = "New name";
		newDepartment.setName(newName);
		newDepartment = departmentRepository.update(newDepartment);
		assertEquals(newDepartment.getName(), newName);
	}

	@Test
	void delete_department() {
		department = departmentRepository.save(department);
		boolean isDeleted = departmentRepository.deleteById(department.getId());
		assertTrue(isDeleted);
	}
}