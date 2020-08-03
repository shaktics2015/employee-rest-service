package com.sg.employeeportal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.employeeportal.dto.EmployeeDTO;
import com.sg.employeeportal.model.Department;
import com.sg.employeeportal.model.Employee;
import com.sg.employeeportal.service.IDepartmentService;
import com.sg.employeeportal.service.IEmployeeService;
import com.sg.employeeportal.util.CustomErrorType;
import com.sg.employeeportal.util.StandardValidationHelper;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/employee")
@CrossOrigin
@Tag(name = "Employee", description = "The Employee API")
public class EmployeeController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private IEmployeeService service;

	@Autowired
	private IDepartmentService departmentService;

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employees = service.findAll();
		if (employees.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

		StandardValidationHelper validationHelper = new StandardValidationHelper();

		validationHelper.objectNotNull("employee", employeeDTO, "Blank request not allowed");

		validationHelper.objectNotNull("firstName", employeeDTO.getFirstName(), "First Name cannot be null.");

		if (employeeDTO.getFirstName() != null)
			validationHelper.isAlpha("firstName", employeeDTO.getFirstName());

		validationHelper.objectNotNull("lastName", employeeDTO.getLastName(), "Last Name cannot be null.");

		if (employeeDTO.getLastName() != null)
			validationHelper.isAlpha("lastName", employeeDTO.getLastName());

		validationHelper.objectNotNull("gender", employeeDTO.getGender(), "Gender cannot be null.");

		validationHelper.objectNotNull("dob", employeeDTO.getDob(), "Date fo Birth cannot be null.");

		validationHelper.objectNotNull("department", employeeDTO.getDepartment(), "Department cannot be null.");

		if (validationHelper.hasValidationErrors()) {
			return new ResponseEntity(validationHelper.getValidationErrors(), HttpStatus.BAD_REQUEST);
		}

		Department department = departmentService.findById(employeeDTO.getDepartment());

		if (department == null) {
			LOG.error("Department not found {}", employeeDTO.getDepartment());
			return new ResponseEntity(
					new CustomErrorType("Unable to create Employee with department ID: " + employeeDTO.getDepartment()),
					HttpStatus.NOT_FOUND);
		}

		service.save(employeeDTO, department);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
