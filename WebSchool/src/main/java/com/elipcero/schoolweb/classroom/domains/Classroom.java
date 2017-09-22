package com.elipcero.schoolweb.classroom.domains;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Classroom  {
	
	private Integer id;

	@NotNull
	@Size(max = 50)
	private String name;
}
