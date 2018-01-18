package com.elipcero.schoolweb.classroom.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
                    break;
                case TUESDAY:
                    edition.setTuesday(true);
                    break;
                case WEDNESDAY:
                    edition.setWednesday(true);
                    break;
                case THURSDAY:
                    edition.setThursday(true);
                    break;
                case FRIDAY:
                    edition.setFriday(true);
                    break;
                case SATURDAY:
                    edition.setSaturday(true);
                    break;
                case SUNDAY:
                    edition.setSunday(true);
                    break;
            }
        });

        return edition;
    }
}
