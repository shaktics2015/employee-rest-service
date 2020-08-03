package com.sg.employeeportal;

import java.util.Date;

import com.sg.employeeportal.enums.Gender;

public interface TestProps {

	Long ID = 1L;

	String FIRST_NAME = "SHAKTI";

	String LAST_NAME = "SINGH";

	Gender GENDER = Gender.MALE;

	Date DOB = new Date();

	String DEPARTMENT_NAME = "DEPARTMENT NAME";
}
