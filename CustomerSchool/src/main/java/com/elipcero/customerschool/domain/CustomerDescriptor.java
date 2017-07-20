package com.elipcero.customerschool.domain;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "descriptor", types = { Customer.class })
public interface CustomerDescriptor {
	Integer getId();
	String getFirstName();
	String getSecondName();
	String getThirdName();
}
