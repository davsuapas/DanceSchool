package com.elipcero.schoolweb.classroom.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassCalendarDay {
    private DayOfWeek dayOfWeek;
    private int numberOfStudents;

    public String getNameOfDay() {
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }
}
