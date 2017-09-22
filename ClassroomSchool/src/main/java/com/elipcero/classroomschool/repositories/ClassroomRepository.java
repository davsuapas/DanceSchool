package com.elipcero.classroomschool.repositories;

import org.springframework.data.repository.CrudRepository;

import com.elipcero.classroomschool.domains.Classroom;

public interface ClassroomRepository extends CrudRepository<Classroom, Integer> {
}
