package com.elipcero.classroomschool.domains;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
public class ClassCalendarView {

    private String classTypeName;
    private String classroomName;

    private LocalTime start;
    private LocalTime end;

    private int numberMaxOfStudents;

    @Setter() private List<ClassCalendarDayView> classCalendarDayView;
}
