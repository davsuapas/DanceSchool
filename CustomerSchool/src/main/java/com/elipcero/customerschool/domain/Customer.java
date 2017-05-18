package com.elipcero.customerschool.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.elipcero.schoolcore.AbstractDomain;

import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
public class Customer extends AbstractDomain {

	@Column(length=30, nullable=false)
	private String firstName;
	
	@Column(length=30, nullable=false)
	private String secondName;

	@Column(length=30, nullable=false)
	private String thirdName;

	@Column(length=100)
	private String email;
}
