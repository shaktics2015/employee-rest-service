package com.sg.employeeportal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sg.employeeportal.exception.UnknownEnumValueException;

public enum Gender {

	MALE(1),

	FEMALE(2);

	private int value;

	@JsonValue
	public int getValue() {
		return value;
	}

	@JsonCreator
	public static Gender of(Integer value) {
		if (null == value) {
			return null;
		}

		for (Gender item : Gender.values()) {
			if (value.equals(item.getValue())) {
				return item;
			}
		}

		throw new UnknownEnumValueException("Gender: unknown value: " + value);
	}

	Gender(int value) {
		this.value = value;
	}

	public static Gender parse(int value) {
		for (Gender e : Gender.values()) {
			if (e.getValue() == value)
				return e;
		}
		return Gender.MALE;
	}
}
