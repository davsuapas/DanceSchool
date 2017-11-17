package com.elipcero.classroomschool.repositories;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import com.elipcero.classroomschool.domains.ClassType;
import com.elipcero.classroomschool.domains.Classroom;
import com.elipcero.classroomschool.domains.ClassroomClassType;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@RepositoryEventHandler
@AllArgsConstructor
public class ClassroomClassTypeEventHandler {
	
	@NonNull private final ClassroomRepository classroomRepository;
	@NonNull private final ClassTypeRepository classTypeRepository;
	
	@HandleBeforeCreate
	public void handlePersonCreate(ClassroomClassType entity) {
		if (entity.getClassroom() == null) {
			Classroom classroom = this.classroomRepository.findOne(entity.getId().getClassroom());
			entity.setClassroom(classroom);
		}
		if (entity.getClassType() == null) {
			ClassType classType = this.classTypeRepository.findOne(entity.getId().getClassType());
			entity.setClassType(classType);
		}
	}	
}
