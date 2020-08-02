package com.sg.employeeportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

 
@SpringBootApplication
@EnableJpaRepositories("com.sg.employeeportal.repository")
public class EmployeeportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeportalApplication.class, args);
	}

}
