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

import com.sg.employeeportal.dto.DepartmentDTO;
import com.sg.employeeportal.model.Department;
import com.sg.employeeportal.service.IDepartmentService;
import com.sg.employeeportal.util.CustomErrorType;
import com.sg.employeeportal.util.StandardValidationHelper;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/department")
@CrossOrigin
@Tag(name = "Department", description = "The Department API")
public class DepartmentController {
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private IDepartmentService service;

	@GetMapping("/all")
	public ResponseEntity<List<Department>> getDepartments() {
		List<Department> departments = service.findAll();
		if (departments.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@RequestBody DepartmentDTO departmentDTO) {
		
		StandardValidationHelper validationHelper = new StandardValidationHelper();

		validationHelper.objectNotNull("name", departmentDTO.getName(), "Name cannot be null.");

		if (validationHelper.hasValidationErrors()) {
			return new ResponseEntity(validationHelper.getValidationErrors(),
					HttpStatus.BAD_REQUEST);
		}
		
		Department department = service.findByName(departmentDTO.getName());

		if (department != null) {
			LOG.error("Unable to create, duplicate  Department name {}", departmentDTO.getName());
			return new ResponseEntity(
					new CustomErrorType("Unable to create, duplicate Department name " + departmentDTO.getName()),
					HttpStatus.CONFLICT);
		}
		department = new Department(departmentDTO.getName());
		service.save(department);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
