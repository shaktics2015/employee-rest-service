package com.sg.employeeportal.service;

import java.util.List;

import com.sg.employeeportal.model.Department;

public interface IDepartmentService {
	List<Department> findAll();

	Department save(Department department);

	List<Department> saveAll(List<Department> departments);

	Department findById(Long id);

	Department findByName(String name);
}
