package com.elipcero.schoolweb.classroom.domains;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
public class ClassCalendarDayView {

    private DayOfWeek dayOfWeek;
    private int numberOfStudents;

    public String getNameOfDay() {
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }
}
