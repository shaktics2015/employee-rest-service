package com.sg.employeeportal.rest.service;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sg.employeeportal.controller.DepartmentController;

@SpringBootTest
@DisplayName("Department rest API test collections")
class DepartmentServiceTest {

	private MockMvc mockMvc;

	@Autowired
	DepartmentController controller;

	String departmentJson = "{\"name\":\"Development and Engineering dups\" }";

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(this.controller).build();
	}

	@Test
	@DisplayName("[SUCCESS] Create Department")
	public void createDepartment() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/department/create").contextPath("/api")
				.accept(MediaType.APPLICATION_JSON).content(departmentJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	@DisplayName("[FAILURE] Create Department, Reason: Department name is missing")
	public void createDepartmentError() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/department/create").contextPath("/api")
				.accept(MediaType.APPLICATION_JSON).content("{}").contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	@DisplayName("[SUCCESS] Get all Departments")
	public void getAllDepartments() throws Exception {
		mockMvc.perform(get("/api/department/all").contextPath("/api").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.*.name", hasItem(is("Development and Engineering"))))
				.andExpect(jsonPath("$.*.name", hasItem(is("Design and Production"))))
				.andExpect(jsonPath("$.*.name", hasItem(is("Training and Maintenance"))))
				.andExpect(jsonPath("$.*.name", hasItem(is("Quality control"))));
	}

}
