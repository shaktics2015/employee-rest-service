package com.sg.employeeportal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sg.employeeportal.TestProps;
import com.sg.employeeportal.model.Department;
import com.sg.employeeportal.repository.DepartmentRepository;

class DepartmentServiceTest implements TestProps {
	@Mock
	private DepartmentRepository repository;

	@InjectMocks
	private DepartmentService service;

	private Department department;

	private List<Department> departments;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockDepartment();
	}

	private void mockDepartment() {
		departments = new ArrayList<Department>();
		department = new Department();
		department.setName(DEPARTMENT_NAME);
		departments.add(department);
	}

	@Test
	public void findAll() {
		doReturn(departments).when(repository).findAll();
		List<Department> response = service.findAll();
		assertNotNull(response);
		assertEquals(1, response.size());
		assertDepartment(response.get(0));

	}

	@Test
	public void findById() {
		doReturn(Optional.ofNullable(department)).when(repository).findById(any(Long.class));
		Department response = service.findById(1L);
		assertNotNull(response);
		assertDepartment(response);
	}

	@Test
	public void findByName() {
		doReturn(department).when(repository).findByName(any(String.class));
		Department response = service.findByName(DEPARTMENT_NAME);
		assertNotNull(response);
		assertDepartment(response);
	}

	@Test
	public void save() {
		doReturn(department).when(repository).save(any(Department.class));
		Department response = service.save(department);
		assertNotNull(response);
		assertDepartment(response);
	}

	private void assertDepartment(Department response) {
		assertEquals(department.getName(), response.getName());
	}
}
