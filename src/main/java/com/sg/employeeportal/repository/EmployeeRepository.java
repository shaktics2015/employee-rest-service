package com.sg.employeeportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sg.employeeportal.model.Employee;

@RepositoryRestResource(exported = false)
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	@Override
	@Query
	List<Employee> findAll();

}
