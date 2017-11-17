package com.elipcero.schoolweb.classroom.domains;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class ClassroomClassType {

	private Classroom classroom = new Classroom();
	private ClassType classType = new ClassType();

	@NotNull
	@Min(1)
	private int classMax;
	
	public String getName() {
		return classroom.getName() + " - " + classType.getName();
	}
	
	public String getId() {
		return classroom.getId() + "_" + classType.getId();
	}
}
