package com.sg.employeeportal.service;

import java.util.List;

import com.sg.employeeportal.dto.EmployeeDTO;
import com.sg.employeeportal.model.Department;
import com.sg.employeeportal.model.Employee;

public interface IEmployeeService {
	List<Employee> findAll();

	Employee save(EmployeeDTO employee, Department department);
}
