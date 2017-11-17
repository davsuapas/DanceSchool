package com.elipcero.classroomschool.domains;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomClassTypeId implements Serializable {

	private static final long serialVersionUID = 1L;

	private int classroom;
	private int classType;
}
