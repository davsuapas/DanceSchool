package com.elipcero.schoolweb.classroom;

import com.elipcero.schoolweb.classroom.domains.ClassCalendar;
import com.elipcero.schoolweb.classroom.services.ClassCalendarService;
import com.elipcero.schoolweb.shared.domain.DomainIterator;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value="/classcalendar")
@AllArgsConstructor
public class ClassCalendarList {

	public static final String CLASSROOM_CLASS_CALENDAR_LIST = "classroom/classcalendar-list";

	private @NotNull final ClassCalendarService classCalendarService;

	@GetMapping(value="/list")
	public String getClassCalendarList(Model model) {
		FillModel(model, classCalendarService.getAll());
		return CLASSROOM_CLASS_CALENDAR_LIST;
	}

	@GetMapping(value="/{id}")
	public String getClassCalendarById(@PathVariable int id, Model model) {
		ClassCalendar classCalendar = this.classCalendarService.getClassCalendarById(id);
		FillModel(model, classCalendar);
		return CLASSROOM_CLASS_CALENDAR_LIST;
	}

	private static void FillModel(Model model, ClassCalendar classCalendar) {
		FillModel(model, Collections.singletonList(classCalendar));
	}
	
	private static void FillModel(Model model, List<ClassCalendar> classCalendars) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		ClassroomMenuBuilder.Fill(model, ClassRoomMenuDomain.EnumMenuOption.ClassCalendar);
		model.addAttribute("classCalendarsRowsCols", DomainIterator.Create(classCalendars, 3));
	}
}
