package com.elipcero.classroomschool.domains;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "all", types = { ClassroomClassType.class }) 
public interface ClassroomClassTypeAllProjection {
	Integer getClassMax();
	Classroom getClassroom();
	ClassType getClassType();
}
