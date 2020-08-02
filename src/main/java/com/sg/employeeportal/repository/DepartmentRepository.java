package com.sg.employeeportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sg.employeeportal.model.Department;

@RepositoryRestResource(exported = false)
public interface DepartmentRepository extends CrudRepository<Department, Long> {
	List<Department> findAll();

	Optional<Department> findById(Long id);

	Department findByName(String name);
}
