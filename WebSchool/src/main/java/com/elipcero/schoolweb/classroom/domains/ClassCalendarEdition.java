package com.elipcero.schoolweb.classroom.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClassCalendarEdition extends ClassCalendarBase {

    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;

    public ClassCalendar convertToClassCalendar() {
        ClassCalendar calendar = new ClassCalendar();
        calendar.setId(getId());
        calendar.setClassroom(getClassroom());
        calendar.setClassType(getClassType());
        calendar.setStart(getStart());
        calendar.setEnd(getEnd());

        Set<ClassCalendarDay> days = new HashSet<>();
        if (monday) days.add(new ClassCalendarDay(DayOfWeek.MONDAY));
        if (tuesday) days.add(new ClassCalendarDay(DayOfWeek.TUESDAY));
        if (wednesday) days.add(new ClassCalendarDay(DayOfWeek.WEDNESDAY));
        if (thursday) days.add(new ClassCalendarDay(DayOfWeek.THURSDAY));
        if (friday) days.add(new ClassCalendarDay(DayOfWeek.FRIDAY));
        if (saturday) days.add(new ClassCalendarDay(DayOfWeek.SATURDAY));
        if (sunday) days.add(new ClassCalendarDay(DayOfWeek.SUNDAY));
        calendar.setClassCalendarDay(days);

        return calendar;
    }
}
