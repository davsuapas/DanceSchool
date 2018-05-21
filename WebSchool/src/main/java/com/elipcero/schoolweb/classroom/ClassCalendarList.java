package com.elipcero.schoolweb.classroom;

import com.elipcero.schoolweb.classroom.domains.ClassCalendarView;
import com.elipcero.schoolweb.classroom.services.ClassCalendarService;
import com.elipcero.schoolweb.shared.domain.DomainIterator;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;
import com.elipcero.schoolweb.shared.web.ExceptionController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value="/classcalendar")
@AllArgsConstructor
public class ClassCalendarList {

	public static final String REDIRECT_CLASSCALENDAR_LIST = "redirect:/classcalendar/list";
	private static final String CLASSROOM_CLASS_CALENDAR_LIST = "classroom/classcalendar-list";

	private @NotNull final ClassCalendarService classCalendarService;

	@GetMapping(value="/list")
	public String getClassCalendarList(Model model) {
		FillModel(model, classCalendarService.getAll());
		return CLASSROOM_CLASS_CALENDAR_LIST;
	}

	@GetMapping(value="/listforselect")
	public String getClassCalendarListForSelect(Model model) {
		FillModel(model, classCalendarService.getAll());
		return "classroom/classcalendar-list-for-select";
	}

	@ExceptionController(
			viewName=REDIRECT_CLASSCALENDAR_LIST,
			messages="400;Existen problemas de comunicación. Inténtelo más tarde"
	)
	@GetMapping(value="/{id}")
	public String getClassCalendarById(@PathVariable int id, Model model, RedirectAttributes redirectAttr) {
		ClassCalendarView classCalendar = this.classCalendarService.getClassCalendarById(id);
		FillModel(model, classCalendar);
		return CLASSROOM_CLASS_CALENDAR_LIST;
	}

	@ExceptionController(
			viewName=REDIRECT_CLASSCALENDAR_LIST,
			messages="409;No se puede eliminar porque existen alumnos asignados a este calendario"
	)
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes redirectAttr) {
		classCalendarService.delete(id);
		return REDIRECT_CLASSCALENDAR_LIST;
	}

	private static void FillModel(Model model, ClassCalendarView classCalendar) {
		FillModel(model, Collections.singletonList(classCalendar));
	}
	
	static void FillModel(Model model, List<ClassCalendarView> classCalendars) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		ClassroomMenuBuilder.Fill(model, ClassRoomMenuDomain.EnumMenuOption.ClassCalendar);
		model.addAttribute("classCalendarsRowsCols", DomainIterator.Create(classCalendars, 3));
	}
}
