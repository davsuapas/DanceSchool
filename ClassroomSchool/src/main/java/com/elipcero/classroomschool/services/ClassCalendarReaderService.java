package com.elipcero.classroomschool.services;

import com.elipcero.classroomschool.domains.ClassCalendar;
import com.elipcero.classroomschool.domains.ClassCustomerDayTotal;
import com.elipcero.classroomschool.repositories.ClassCalendarRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassCalendarReaderService {

    @NonNull private final ClassCustomerTotalViewResource resource;
    @NonNull private final ClassCalendarRepository repository;

    public List<ClassCalendar> getClassCalendar() {
        List<ClassCalendar> calendars = new ArrayList<>();

        for (ClassCalendar calendar : repository.findAll()) {
            ClassCustomerDayTotal daysSummary = resource.getCalendarSummaryByClassId(calendar.getId()).getContent();
            if (daysSummary != null) {
                calendar.getClassCalendarDay().forEach(d -> {
                    switch (d.getDayOfWeek()) {
                        case MONDAY: d.setNumberOfStudents(daysSummary.getCustomerTotalDay1()); break;
                        case TUESDAY: d.setNumberOfStudents(daysSummary.getCustomerTotalDay2()); break;
                        case WEDNESDAY: d.setNumberOfStudents(daysSummary.getCustomerTotalDay3()); break;
                        case THURSDAY: d.setNumberOfStudents(daysSummary.getCustomerTotalDay4()); break;
                        case FRIDAY: d.setNumberOfStudents(daysSummary.getCustomerTotalDay5()); break;
                        case SATURDAY: d.setNumberOfStudents(daysSummary.getCustomerTotalDay6()); break;
                        case SUNDAY: d.setNumberOfStudents(daysSummary.getCustomerTotalDay7()); break;
                    }
                });
            }
            calendars.add(calendar);
        }
        return calendars;
    }
}
