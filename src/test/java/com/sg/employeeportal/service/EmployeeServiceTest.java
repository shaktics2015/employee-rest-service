package com.sg.employeeportal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sg.employeeportal.TestProps;
import com.sg.employeeportal.dto.EmployeeDTO;
import com.sg.employeeportal.model.Department;
import com.sg.employeeportal.model.Employee;
import com.sg.employeeportal.repository.EmployeeRepository;

public class EmployeeServiceTest implements TestProps {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	private List<Employee> employees;

	private Employee employee;

	private EmployeeDTO employeeDTO;

	private Department department;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockEmp();
		mockEmployeeDTO();
		mockDepartment();
	}

	private void mockEmp() {
		employees = new ArrayList<Employee>();
		employee = new Employee();
		employee.setFirstName(FIRST_NAME);
		employee.setLastName(LAST_NAME);
		employee.setFirstName(FIRST_NAME);
		employee.setGender(GENDER);
		employee.setDob(DOB);
		Department department = new Department();
		department.setName(DEPARTMENT_NAME);
		employee.setDepartment(department);
		employees.add(employee);
	}

	private void mockEmployeeDTO() {
		employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName(FIRST_NAME);
		employeeDTO.setLastName(LAST_NAME);
		employeeDTO.setFirstName(FIRST_NAME);
		employeeDTO.setGender(1);
		employeeDTO.setDob(DOB);
	}

	private void mockDepartment() {
		department = new Department();
		department.setName(DEPARTMENT_NAME);
	}

	@Test
	public void findAll() {
		doReturn(employees).when(employeeRepository).findAll();
		List<Employee> response = employeeService.findAll();
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEmployee(response.get(0));
	}

	@Test
	public void save() {
		doReturn(employee).when(employeeRepository).save(any(Employee.class));
		Employee response = employeeService.save(employeeDTO, department);
		assertNotNull(response);
		assertEmployee(response);
	}
	
	private void assertEmployee(Employee employeeRes ) {
		assertEquals(employee.getFirstName(), employeeRes.getFirstName());
		assertEquals(employee.getLastName(), employeeRes.getLastName());
		assertEquals(employee.getGender(), employeeRes.getGender());
		assertEquals(employee.getDob(), employeeRes.getDob());
		assertEquals(employee.getDepartment(), employeeRes.getDepartment());
	}
}
