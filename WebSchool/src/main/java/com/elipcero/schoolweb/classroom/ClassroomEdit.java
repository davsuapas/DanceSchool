package com.elipcero.schoolweb.classroom;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elipcero.schoolweb.classroom.domains.Classroom;
import com.elipcero.schoolweb.classroom.services.ClassroomService;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Controller
@RequestMapping(value="/classroom")
@AllArgsConstructor
public class ClassroomEdit {
	
	private static final String VIEWNAME_CLASSROOM_EDIT = "classroom/classroom-edit";
	
	private @NonNull final ClassroomService classroomService;

	@GetMapping(value="/add")
	public String add(Model model) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		model.addAttribute("classroom", new Classroom());
		return VIEWNAME_CLASSROOM_EDIT;
	}
	
	@GetMapping(value="/update/{id}")
	public String update(Model model, @PathVariable int id) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		model.addAttribute("classroom", classroomService.getClassroomById(id));
		return VIEWNAME_CLASSROOM_EDIT;
	}
	
	@PostMapping(value="/save")
	public String save(@Valid Classroom classroom, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
			return VIEWNAME_CLASSROOM_EDIT;
		}
		
		Integer classroomId = classroomService.save(classroom);
		
		return String.format("redirect:/classroom/%s", classroomId);
	}
}
