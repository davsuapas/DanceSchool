package com.elipcero.schoolweb.classroom;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elipcero.schoolweb.classroom.domains.ClassroomClassType;
import com.elipcero.schoolweb.classroom.services.ClassroomClassTypeService;
import com.elipcero.schoolweb.shared.domain.DomainIterator;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value="/classroomclasstype")
@AllArgsConstructor
public class ClassroomClassTypeList {
	
	private @NotNull final ClassroomClassTypeService classroomClassTypeService;

	@GetMapping(value="/list")
	public String getClassroomClassTypeList(Model model) {
		FillModel(model, classroomClassTypeService.getAll());
		return "classroom/classroom-classtype-list";
	}
	
	@GetMapping(value="/{id}")
	public String getClassroomClassTypeById(@PathVariable String id, Model model) {
		ClassroomClassType classroomClassType = this.classroomClassTypeService.getById(id);
		FillModel(model, classroomClassType);
		return "classroom/classroom-classtype-list";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable String id) {
		classroomClassTypeService.delete(id);
		return "redirect:/classroomclasstype/list";
	}
	
	private static void FillModel(Model model, ClassroomClassType classroomClassTypes) {
		FillModel(model, Arrays.asList(classroomClassTypes));
	}
	
	private static void FillModel(Model model, List<ClassroomClassType> classroomClassTypes) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		ClassroomMenuBuilder.Fill(model, ClassRoomMenuDomain.EnumMenuOption.ClassroomClassType);
		model.addAttribute("classroomclassTypeRowsCols", DomainIterator.Create(classroomClassTypes, 3));
	}
}
