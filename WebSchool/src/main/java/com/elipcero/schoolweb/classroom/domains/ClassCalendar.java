package com.elipcero.schoolweb.classroom.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClassCalendar {

    private int id;

    private Classroom classroom;
    private ClassType classType;
    private LocalTime start;
    private LocalTime end;

    private Set<ClassCalendarDay> calendarDays;
    private ClassroomClassType classroomClassType;

    public String getName() {
        return this.classroom.getName() + " - " + this.classType.getName();
    }
}
