package com.elipcero.schoolweb.classroom.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClassCalendar extends ClassCalendarBase {

    private Set<ClassCalendarDay> classCalendarDay;
}
