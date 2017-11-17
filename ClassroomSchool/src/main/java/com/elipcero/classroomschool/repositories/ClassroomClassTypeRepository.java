package com.elipcero.classroomschool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.elipcero.classroomschool.domains.ClassroomClassType;
import com.elipcero.classroomschool.domains.ClassroomClassTypeAllProjection;
import com.elipcero.classroomschool.domains.ClassroomClassTypeId;

@RepositoryRestResource(excerptProjection = ClassroomClassTypeAllProjection.class)
public interface ClassroomClassTypeRepository extends CrudRepository<ClassroomClassType, ClassroomClassTypeId> {
}
