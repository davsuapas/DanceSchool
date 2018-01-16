package com.elipcero.schoolweb.classroom;

import com.elipcero.schoolweb.classroom.domains.ClassCalendarEdition;
import com.elipcero.schoolweb.classroom.domains.ClassroomClassType;
import com.elipcero.schoolweb.classroom.services.ClassCalendarService;
import com.elipcero.schoolweb.classroom.services.ClassTypeService;
import com.elipcero.schoolweb.classroom.services.ClassroomService;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(value="/classcalendar")
@AllArgsConstructor
public class ClassCalendarEdit {

	private static final String VIEWNAME_CLASSCALENDAR_EDIT = "classroom/classcalendar-edit";

	private @NonNull final ClassCalendarService classCalendarService;
	private @NotNull final ClassroomService classroomService;
	private @NotNull final ClassTypeService classTypeService;

	@GetMapping(value="/add")
	public String add(Model model) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		model.addAttribute("classCalendar", new ClassCalendarEdition());
		addReferencesToModel(model);
		return VIEWNAME_CLASSCALENDAR_EDIT;
	}

	@GetMapping(value="/update/{id}")
	public String update(Model model, @PathVariable int id) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		model.addAttribute("classCalendar", classCalendarService.getClassCalendarById(id).convertToEdition());
		return VIEWNAME_CLASSCALENDAR_EDIT;
	}

	private void addReferencesToModel(Model model) {
		model.addAttribute("classrooms", this.classroomService.getAll());
		model.addAttribute("classTypes", this.classTypeService.getAll());
	}

	@PostMapping(value="/save/")
	public String save(
			@Valid @ModelAttribute("classCalendar") ClassCalendarEdition classCalendarEdition,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
			addReferencesToModel(model);
			return VIEWNAME_CLASSCALENDAR_EDIT;
		}

		if (classCalendarEdition.isNew()) {
			if (classCalendarService.IsDuplicated(classCalendarEdition)) {
				bindingResult.reject("duplicated", "Ya existe un registro configurado para con este mismo tipo de clase y aula");
				addReferencesToModel(model);
				return VIEWNAME_CLASSCALENDAR_EDIT;
			}
		}

		int classCalendarId = classCalendarService.save(classCalendarEdition);

		return String.format("redirect:/classcalendar/%s", classCalendarId);
	}
}
