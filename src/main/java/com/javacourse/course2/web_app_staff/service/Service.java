package com.javacourse.course2.web_app_staff.service;

import java.util.UUID;

import com.javacourse.course2.web_app_staff.model.SimpleEntity;

public interface Service {
	SimpleEntity save(SimpleEntity simpleEntity);

    SimpleEntity findById(UUID uuid);
}
