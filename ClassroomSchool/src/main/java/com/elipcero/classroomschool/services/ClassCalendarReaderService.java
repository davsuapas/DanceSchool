package com.elipcero.classroomschool.services;

import com.elipcero.classroomschool.domains.*;
import com.elipcero.classroomschool.repositories.ClassCalendarRepository;
import com.elipcero.classroomschool.repositories.ClassroomClassTypeRepository;
import feign.FeignException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassCalendarReaderService {

    @NonNull private final ClassCustomerTotalViewResource resource;
    @NonNull private final ClassCalendarRepository classCalendarRepository;
    @NonNull private final ClassroomClassTypeRepository classroomClassTypeRepository;

    public Optional<ClassCalendarView> getClassCalendarView(int id) {
        return classCalendarRepository.findById(id).map(c -> buildClassCalendarView(c));
    }

    public List<ClassCalendarView> getClassCalendar() {
        List<ClassCalendarView> calendars = new ArrayList<>();

        for (ClassCalendar calendar : classCalendarRepository.findAll()) {
            calendars.add(buildClassCalendarView(calendar));
        }
        return calendars;
    }

    private ClassCalendarView buildClassCalendarView(ClassCalendar calendar) {
        ClassCalendarView calendarView = ClassCalendarView.builder()
                .id(calendar.getId())
                .classroomName(calendar.getClassroom().getName())
                .classTypeName(calendar.getClassType().getName())
                .start(calendar.getStart())
                .end(calendar.getEnd())
                .numberMaxOfStudents(classroomClassTypeRepository.findById(
                        new ClassroomClassTypeId(calendar.getClassroom().getId(), calendar.getClassType()
                                .getId())).get().getClassMax())
                .build();

        ClassCustomerDayTotal daysSummary = getCalendarSummaryByClassId(calendar);
        calendarView.setClassCalendarDayView(
            calendar.getClassCalendarDay().stream().map(d -> {
                int numberOfStudents = 0;
                if (daysSummary != null) {
                    switch (d.getDayOfWeek()) {
                        case MONDAY: numberOfStudents = daysSummary.getCustomerTotalDay1(); break;
                        case TUESDAY: numberOfStudents = daysSummary.getCustomerTotalDay2(); break;
                        case WEDNESDAY: numberOfStudents = daysSummary.getCustomerTotalDay3(); break;
                        case THURSDAY: numberOfStudents = daysSummary.getCustomerTotalDay4(); break;
                        case FRIDAY: numberOfStudents = daysSummary.getCustomerTotalDay5(); break;
                        case SATURDAY: numberOfStudents = daysSummary.getCustomerTotalDay6(); break;
                        case SUNDAY: numberOfStudents = daysSummary.getCustomerTotalDay7(); break;
                    }
                }
                return ClassCalendarDayView.builder()
                        .dayOfWeek(d.getDayOfWeek())
                        .numberOfStudents(numberOfStudents)
                        .build();
        }).collect(Collectors.toList()));
        return calendarView;
    }

    private ClassCustomerDayTotal getCalendarSummaryByClassId(ClassCalendar calendar) {
        try {
            return resource.getCalendarSummaryByClassId(calendar.getId()).getContent();
        }
        catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                return null;
            }
            else {
                throw ex;
            }
        }
    }
}
