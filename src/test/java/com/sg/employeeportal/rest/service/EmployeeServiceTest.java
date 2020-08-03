package com.sg.employeeportal.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.sg.employeeportal.controller.EmployeeController;

@SpringBootTest
@DisplayName("Employee rest API test collections")
class EmployeeServiceTest{

	private MockMvc mockMvc;

	@Autowired
	EmployeeController controller;

	String employeeJson = "{\n" + "    \"firstName\": \"Shakti\",\n" + "    \"lastName\": \"Singh\",\n"
			+ "    \"gender\": 2,\n" + "    \"dob\": \"2020-08-02T10:22:07.012+00:00\",\n" + "    \"department\": 1\n"
			+ "}";

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(this.controller).build();
	}

	@Test
	@DisplayName("[SUCCESS] Create Employee")
	public void createEmployee() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employee/create").contextPath("/api")
				.accept(MediaType.APPLICATION_JSON).content(employeeJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	@DisplayName("[FAILURE] Create Employee, Reason: Employee attributes are missing")
	public void createEmployeeError() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/employee/create").contextPath("/api")
				.accept(MediaType.APPLICATION_JSON).content("{}").contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

	@Test
	@DisplayName("[SUCCESS] Get all Employees")
	public void getAllEmployees() throws Exception {
		mockMvc.perform(get("/api/employee/all").contextPath("/api").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
}