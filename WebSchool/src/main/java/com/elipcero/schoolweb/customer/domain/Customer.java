package com.elipcero.schoolweb.customer.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Customer extends CustomerDescriptor {

	@NotNull
	@Email
	private String email;
}
