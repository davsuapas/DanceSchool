package com.elipcero.classroomschool.domains;

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalTime;
import java.util.Set;

@Projection(name = "all", types = { ClassCalendar.class })
public interface ClassCalendarAllProjection {
    int getId();
    Classroom getClassroom();
    ClassType getClassType();
    LocalTime getStart();
    LocalTime getEnd();
    Set<ClassCalendarDay> getClassCalendarDay();
}
