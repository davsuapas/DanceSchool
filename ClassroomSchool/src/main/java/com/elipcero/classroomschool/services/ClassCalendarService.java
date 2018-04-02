package com.elipcero.classroomschool.services;

import com.elipcero.classroomschool.domains.ClassCalendar;
import com.elipcero.classroomschool.domains.ClassCalendarDay;
import com.elipcero.classroomschool.repositories.ClassCalendarRepository;
import com.elipcero.classroomschool.repositories.ClassTypeRepository;
import com.elipcero.classroomschool.repositories.ClassroomRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClassCalendarService {

	private @NonNull final ClassCalendarRepository repository;
	private @NonNull final ClassroomRepository classroomRepository;
	private @NonNull final ClassTypeRepository classTypeRepository;

	public Optional<ClassCalendar> upgradeCalendar(ClassCalendar classCalendarForMerging) {

		ClassCalendar classCalendarSaved;

		if (classCalendarForMerging.getId() == null) {
			classCalendarForMerging.setClassroom(classroomRepository.findById(classCalendarForMerging.getClassroom().getId()).get());
			classCalendarForMerging.setClassType(classTypeRepository.findById(classCalendarForMerging.getClassType().getId()).get());
			classCalendarSaved = repository.save(classCalendarForMerging);
		}
		else {
			ClassCalendar classCalendarSource = repository.findById(classCalendarForMerging.getId()).get();
			classCalendarSource.setStart(classCalendarForMerging.getStart());
			classCalendarSource.setEnd(classCalendarForMerging.getEnd());

			if (classCalendarSource != null) {
				delete(classCalendarForMerging, classCalendarSource);
				add(classCalendarForMerging, classCalendarSource);
				classCalendarSaved = repository.save(classCalendarSource);
			}
			else {
				return Optional.empty();
			}
		}

		return Optional.of(classCalendarSaved);
	}

	private static void add(ClassCalendar classCalendarForMerging, ClassCalendar classCalendarSource) {
		classCalendarForMerging.getClassCalendarDay()
			.forEach(c -> {
				Optional<ClassCalendarDay> seekDay =
						classCalendarSource.getClassCalendarDay().stream()
							.filter(co -> co.getDayOfWeek().equals(c.getDayOfWeek())).findFirst();
				if (!seekDay.isPresent()) {
					classCalendarSource.getClassCalendarDay().add(c);
				}
			});
	}

	private static void delete(ClassCalendar classCalendarForMerging, ClassCalendar classCalendarSource) {
		List<ClassCalendarDay> forRemoving = new ArrayList<ClassCalendarDay>();
		classCalendarSource.getClassCalendarDay()
			.forEach(c -> {
				Optional<ClassCalendarDay> seekDay =
						classCalendarForMerging.getClassCalendarDay().stream()
							.filter(co -> co.getDayOfWeek().equals(c.getDayOfWeek())).findFirst();
				if (!seekDay.isPresent()) {
					forRemoving.add(c);
				}
			});
		classCalendarSource.getClassCalendarDay().removeAll(forRemoving);
	}

	public List<ClassCalendar> getClassCalendar() {
		List<ClassCalendar> calendars = new ArrayList<>();
		for (ClassCalendar calendar : repository.findAll()) {

			calendars.add(calendar);
		}
		return calendars;
	}
}
