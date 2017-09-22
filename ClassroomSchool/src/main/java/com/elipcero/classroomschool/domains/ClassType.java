package com.elipcero.classroomschool.domains;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.elipcero.schoolcore.AbstractDomain;

import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString(callSuper = true)
public class ClassType extends AbstractDomain {

	@Column(length=50, nullable=false)
	private String name;
}

