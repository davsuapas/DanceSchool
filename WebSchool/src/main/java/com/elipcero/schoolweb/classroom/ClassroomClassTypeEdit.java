package com.elipcero.schoolweb.classroom;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import com.elipcero.schoolweb.classroom.domains.ClassType;
import com.elipcero.schoolweb.classroom.domains.Classroom;
import com.elipcero.schoolweb.classroom.domains.ClassroomClassType;
import com.elipcero.schoolweb.classroom.services.ClassTypeService;
import com.elipcero.schoolweb.classroom.services.ClassroomClassTypeService;
import com.elipcero.schoolweb.classroom.services.ClassroomService;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value="/classroomclasstype")
@AllArgsConstructor
public class ClassroomClassTypeEdit {

	private static final String VIEWNAME_CLASSROOM_CLASSTYPE_EDIT = "classroom/classroom-classtype-edit";
	private @NotNull final ClassroomClassTypeService classroomClassTypeService;
	private @NotNull final ClassroomService classroomService;
	private @NotNull final ClassTypeService classTypeService;

	@GetMapping(value="/update/{id}")
	public String update(Model model, @PathVariable String id) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		model.addAttribute("classroomClassType", classroomClassTypeService.getById(id));
		model.addAttribute("mode", "updating");
		return VIEWNAME_CLASSROOM_CLASSTYPE_EDIT;
	}
	
	@GetMapping(value="/add")
	public String add(Model model) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		model.addAttribute("classroomClassType", new ClassroomClassType());
		model.addAttribute("classrooms", this.classroomService.getAll());
		model.addAttribute("classTypes", this.classTypeService.getAll());
		model.addAttribute("mode", "adding");
		return VIEWNAME_CLASSROOM_CLASSTYPE_EDIT;
	}
	
	@PostMapping(value="/save/{mode}")
	public String save(
			@PathVariable String mode,
			@Valid ClassroomClassType classroomClassType,
			BindingResult bindingResult,
			Model model,
			NativeWebRequest webRequest) throws JsonProcessingException {
	
		IncludeId(webRequest, classroomClassType);
		
		if (bindingResult.hasErrors()) {
			ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
			return VIEWNAME_CLASSROOM_CLASSTYPE_EDIT;
		}
		
		String idResource = classroomClassTypeService.save(mode, classroomClassType);
		
		return String.format("redirect:/classroomclasstype/%s", idResource);
	}
	
	private static void IncludeId(NativeWebRequest webRequest, ClassroomClassType classroomClassType) {
		
		String id = webRequest.getParameter("id");
		
		if (id != null) {
			String[] idArray = id.split("_");
			classroomClassType.setClassroom(new Classroom(Integer.parseInt(idArray[0])));
			classroomClassType.setClassType(new ClassType(Integer.parseInt(idArray[1])));
		}
	}
}
