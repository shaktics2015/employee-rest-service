package com.sg.employeeportal.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.sg.employeeportal.model.Department;
import com.sg.employeeportal.service.IDepartmentService;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private IDepartmentService departmentService;

	public void run(ApplicationArguments args) {
		insertDepartments();
	}

	private void insertDepartments() {
		String deptName = "Development and Engineering";
		List<Department> departments = Arrays.asList(new Department(deptName), new Department("Design and Production"),
				new Department("Training and Maintenance"), new Department("Quality control"));
		departmentService.saveAll(departments);
	}

}