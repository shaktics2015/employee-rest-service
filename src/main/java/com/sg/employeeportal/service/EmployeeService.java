package com.sg.employeeportal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.employeeportal.dto.EmployeeDTO;
import com.sg.employeeportal.enums.Gender;
import com.sg.employeeportal.model.Department;
import com.sg.employeeportal.model.Employee;
import com.sg.employeeportal.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository repository;

	@Override
	public List<Employee> findAll() {
		return repository.findAll();
	}

	@Override
	public Employee save(EmployeeDTO employeeDTO, Department department) {
		Employee employee = new Employee();
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		employee.setGender(Gender.parse(employeeDTO.getGender()));
		employee.setDob(employeeDTO.getDob());
		LOG.info("save Employee : {}", employee);
		employee.setDepartment(department);
		return repository.save(employee);
	} 
}
