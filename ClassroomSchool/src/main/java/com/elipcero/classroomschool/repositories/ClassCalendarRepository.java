package com.elipcero.classroomschool.repositories;

import com.elipcero.classroomschool.domains.ClassCalendar;
import com.elipcero.classroomschool.domains.ClassCalendarAllProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = ClassCalendarAllProjection.class)
public interface ClassCalendarRepository extends CrudRepository<ClassCalendar, Integer> {

    @Query("select count(c.id) from ClassCalendar c where c.classroom.id = :classroomId and c.classType.id = :classTypeId")
    int numberOfRecords(@Param("classroomId") int classroomId, @Param("classTypeId") int classTypeId);
}
