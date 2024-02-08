package com.javacourse.course2.web_app_staff.repository;

public interface SimpleRepository<T, K> {

	T save(T t);

	T findById(K id);

	boolean deleteById(K id);

	T update(T t);

}