package com.elipcero.schoolweb.classroom;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elipcero.schoolweb.classroom.domains.ClassType;
import com.elipcero.schoolweb.classroom.services.ClassTypeService;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Controller
@RequestMapping(value="/classtype")
@AllArgsConstructor
public class ClassTypeEdit {
	
	private @NonNull final ClassTypeService classTypeService;

	@GetMapping(value="/add")
	public String add(Model model) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		model.addAttribute("classType", new ClassType());
		return "classroom/classtype-edit";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(Model model, @PathVariable Integer id) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		model.addAttribute("classType", classTypeService.getClassTypeById(id));
		return "classroom/classtype-edit";
	}
	
	@PostMapping(value="/save")
	public String save(@Valid ClassType classType, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
			return "classroom/classtype-edit";
		}
		
		Integer classTypeId = classTypeService.save(classType);
		
		return String.format("redirect:/classtype/%s", classTypeId);
	}
}
