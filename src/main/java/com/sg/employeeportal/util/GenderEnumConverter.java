package com.sg.employeeportal.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.sg.employeeportal.enums.Gender;

@Component
public class GenderEnumConverter implements Converter<String, Gender> {
	@Override
	public Gender convert(String value) {
		return Gender.of(Integer.valueOf(value));
	}
}