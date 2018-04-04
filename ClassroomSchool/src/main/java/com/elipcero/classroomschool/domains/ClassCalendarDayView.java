package com.elipcero.classroomschool.domains;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;

@Builder
@Getter
public class ClassCalendarDayView {

    private DayOfWeek dayOfWeek;
    private int numberOfStudents;
}
