package com.elipcero.schoolweb.classroom;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elipcero.schoolweb.classroom.domains.ClassType;
import com.elipcero.schoolweb.classroom.services.ClassTypeService;
import com.elipcero.schoolweb.shared.domain.DomainIterator;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value="/classtype")
@AllArgsConstructor
public class ClassTypeList {
	
	@Autowired
	private @NotNull ClassTypeService classTypeService;

	@GetMapping(value="/list")
	public String getClassTypeList(Model model) {
		FillModel(model, classTypeService.getAll());
		return "classroom/classtype-list";
	}
	
	@GetMapping(value="/{id}")
	public String getClassTypeById(@PathVariable Integer id, Model model) {
		ClassType classType = this.classTypeService.getClassTypeById(id);
		FillModel(model, classType);
		return "classroom/classtype-list";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable Integer id) {
		classTypeService.delete(id);
		return "redirect:/classtype/list";
	}

	private static void FillModel(Model model, ClassType classTypes) {
		FillModel(model, Arrays.asList(classTypes));
	}
	
	private static void FillModel(Model model, List<ClassType> classTypes) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		ClassroomMenuBuilder.Fill(model, ClassRoomMenuDomain.EnumMenuOption.ClassType);
		model.addAttribute("classtypesRowsCols", DomainIterator.Create(classTypes, 3));
	}
}
