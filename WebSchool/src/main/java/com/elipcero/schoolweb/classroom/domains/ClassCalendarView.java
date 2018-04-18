package com.elipcero.schoolweb.classroom.domains;

import lombok.Getter;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClassCalendarView {

    private int id;

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

    public List<ClassCalendarDayView> getCalendarDaySorted() {
        return classCalendarDayView.stream()
                .sorted(Comparator.comparing(ClassCalendarDayView::getDayOfWeek))
                .collect(Collectors.toList());
    }

    public boolean canRemove() {
        return classCalendarDayView.stream().allMatch(day -> day.getNumberOfStudents() == 0);
    }

    public boolean isMaxNumberOfStudents() {
        return classCalendarDayView.stream()
                .max(Comparator.comparing(ClassCalendarDayView::getNumberOfStudents))
                .get()
                .getNumberOfStudents() >= numberMaxOfStudents;
    }

    public ClassCalendarEdition convertToEdition() {
        ClassCalendarEdition edition = new ClassCalendarEdition();
        edition.setId(getId());
        edition.setStart(getStart());
        edition.setEnd(getEnd());

        classCalendarDayView.forEach(day -> {
            switch (day.getDayOfWeek()) {
                case MONDAY:
                    edition.setCanEditMonday(day.getNumberOfStudents() == 0);
                    edition.setMonday(true);
                    break;
                case TUESDAY:
                    edition.setCanEditTuesday(day.getNumberOfStudents() == 0);
                    edition.setTuesday(true);
                    break;
                case WEDNESDAY:
                    edition.setCanEditWednesday(day.getNumberOfStudents() == 0);
                    edition.setWednesday(true);
                    break;
                case THURSDAY:
                    edition.setCanEditThursday(day.getNumberOfStudents() == 0);
                    edition.setThursday(true);
                    break;
                case FRIDAY:
                    edition.setCanEditFriday(day.getNumberOfStudents() == 0);
                    edition.setFriday(true);
                    break;
                case SATURDAY:
                    edition.setCanEditSaturday(day.getNumberOfStudents() == 0);
                    edition.setSaturday(true);
                    break;
                case SUNDAY:
                    edition.setCanEditSunday(day.getNumberOfStudents() == 0);
                    edition.setSunday(true);
                    break;
            }
        });

        return edition;
    }
}
