package com.elipcero.schoolweb.classroom;

import com.elipcero.schoolweb.classroom.domains.ClassCalendarModel;
import com.elipcero.schoolweb.classroom.services.ClassCalendarService;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/classroom")
@AllArgsConstructor
public class ClassCalendarEdit {

	private static final String VIEWNAME_CLASSROOM_EDIT = "classroom/classroom-edit";

	private @NonNull final ClassCalendarService classCalendarService;

	@GetMapping(value="/add")
	public String add(Model model) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		model.addAttribute("classCalendar", new ClassCalendarModel());
		return VIEWNAME_CLASSROOM_EDIT;
	}
}
