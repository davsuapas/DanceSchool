package com.elipcero.schoolweb.classroom.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassCalendarDay {
    private DayOfWeek dayOfWeek;
}
