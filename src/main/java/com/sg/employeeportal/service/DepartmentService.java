package com.sg.employeeportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.employeeportal.model.Department;
import com.sg.employeeportal.repository.DepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private DepartmentRepository repository;

	@Override
	public List<Department> findAll() {
		return repository.findAll();
	}

	@Override
	public Department save(Department repartment) {
		return repository.save(repartment);
	}

	@Override
	public Department findById(Long id) {
		Optional<Department> department = repository.findById(id);
		return department.isPresent() ? repository.findById(id).get() : null;
	}

	@Override
	public Department findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Department> saveAll(List<Department> departments) {
		return (List<Department>) repository.saveAll(departments);
	}
}
