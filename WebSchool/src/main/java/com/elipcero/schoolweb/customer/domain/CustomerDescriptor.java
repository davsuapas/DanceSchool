package com.elipcero.schoolweb.customer.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerDescriptor {
	
	private Integer id;
	
	@NotNull
	@Size(max = 30)
	private String firstName;
	
	@NotNull
	@Size(max = 30)
	private String secondName;
	
	@NotNull
	@Size(max = 30)
	private String thirdName;

	public String fullName() {
		return firstName + ", " + getSurname();
	}

	public String getSurname() {
		return secondName + ", " + thirdName;
	}
}
