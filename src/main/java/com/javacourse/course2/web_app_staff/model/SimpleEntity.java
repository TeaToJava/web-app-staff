package com.javacourse.course2.web_app_staff.model;

import java.util.UUID;

public class SimpleEntity {

	private UUID id;

	public SimpleEntity() {
	}

	public SimpleEntity(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}