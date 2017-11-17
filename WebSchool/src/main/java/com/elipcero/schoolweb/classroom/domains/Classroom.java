package com.elipcero.schoolweb.classroom.domains;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Classroom  {
	
	public Classroom(Integer id) {
		this.id = id;
	}
	
	private Integer id;

	@NotNull
	@Size(max = 50)
	private String name;
}
