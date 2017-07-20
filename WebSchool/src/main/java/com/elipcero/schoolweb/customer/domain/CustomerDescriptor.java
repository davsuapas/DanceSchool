package com.elipcero.schoolweb.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerDescriptor {
	private final Integer id;
	private final String firstName;
	private final String secondName;
	private final String thirdName;
	
	public String getSurname() {
		return secondName + ", " + thirdName;
	}
}
