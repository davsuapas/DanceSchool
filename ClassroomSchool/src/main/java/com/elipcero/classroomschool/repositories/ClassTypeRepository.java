package com.elipcero.classroomschool.repositories;

import org.springframework.data.repository.CrudRepository;

import com.elipcero.classroomschool.domains.ClassType;

public interface ClassTypeRepository extends CrudRepository<ClassType, Integer> {
}
