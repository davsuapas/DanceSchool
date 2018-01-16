package com.elipcero.schoolweb.classroom.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ClassCalendar extends ClassCalendarBase {
    private Set<ClassCalendarDay> classCalendarDay;

    public List getCalendarDayOrder() {
        return classCalendarDay.stream()
                .sorted(Comparator.comparing(ClassCalendarDay::getDayOfWeek))
                .collect(Collectors.toList());
    }

    public ClassCalendarEdition convertToEdition() {
        ClassCalendarEdition edition = new ClassCalendarEdition();
        edition.setId(getId());
        edition.setStart(getStart());
        edition.setEnd(getEnd());
        edition.setClassroom(getClassroom());
        edition.setClassType(getClassType());

        classCalendarDay.stream().forEach(day -> {
            switch (day.getDayOfWeek()) {
                case MONDAY:
                    edition.setMonday(true);
                case TUESDAY:
                    edition.setTuesday(true);
                case WEDNESDAY:
                    edition.setWednesday(true);
                case THURSDAY:
                    edition.setTuesday(true);
                case FRIDAY:
                    edition.setFriday(true);
                case SATURDAY:
                    edition.setSaturday(true);
                case SUNDAY:
                    edition.setSunday(true);
            }
        });

        return edition;
    }
}
