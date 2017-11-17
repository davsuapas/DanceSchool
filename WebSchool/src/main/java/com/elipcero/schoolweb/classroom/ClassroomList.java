package com.elipcero.schoolweb.classroom;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elipcero.schoolweb.classroom.domains.Classroom;
import com.elipcero.schoolweb.classroom.services.ClassroomService;
import com.elipcero.schoolweb.shared.domain.DomainIterator;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;
import com.elipcero.schoolweb.shared.web.ExceptionController;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value="/classroom")
@AllArgsConstructor
public class ClassroomList {
	
	private static final String REDIRECT_CLASSROOM_LIST = "redirect:/classroom/list";
	
	private @NotNull final ClassroomService classroomService;

	@GetMapping(value="/list")
	public String getClassroomList(Model model) {
		FillModel(model, classroomService.getAll());
		return "classroom/classroom-list";
	}
	
	@GetMapping(value="/{id}")
	public String getClassroomById(@PathVariable Integer id, Model model) {
		Classroom classroom = this.classroomService.getClassroomById(id);
		FillModel(model, classroom);
		return "classroom/classroom-list";
	}
	
	@ExceptionController(
			viewName=REDIRECT_CLASSROOM_LIST,
			messages="409;No se puede eliminar porque existen configuraciones de clases asignadas"
	)
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes redirectAttr) {
		classroomService.delete(id);
		return REDIRECT_CLASSROOM_LIST;
	}

	private static void FillModel(Model model, Classroom classrooms) {
		FillModel(model, Arrays.asList(classrooms));
	}
	
	private static void FillModel(Model model, List<Classroom> classrooms) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		ClassroomMenuBuilder.Fill(model, ClassRoomMenuDomain.EnumMenuOption.Classroom);
		model.addAttribute("classroomsRowsCols", DomainIterator.Create(classrooms, 3));
	}
}
