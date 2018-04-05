package com.elipcero.schoolweb.classroom.domains;

import lombok.Getter;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClassCalendarView {

    private String classroomName;
    private String classTypeName;

    private LocalTime start;
    private LocalTime end;

    private int numberMaxOfStudents;

    private List<ClassCalendarDayView> classCalendarDayView;

    public String getName() {
        return this.classroomName + " - " + this.classTypeName;
    }
    public String getDisplayTime() { return this.start + " - " + this.end; }

    public List<ClassCalendarDayView> getCalendarDayOrder() {
        return classCalendarDayView.stream()
                .sorted(Comparator.comparing(ClassCalendarDayView::getDayOfWeek))
                .collect(Collectors.toList());
    }
}
