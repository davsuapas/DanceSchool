package com.elipcero.schoolweb.classroom;

import org.springframework.ui.Model;

public class ClassroomMenuBuilder {
	
	public static void Fill(Model model, ClassRoomMenuDomain.EnumMenuOption option) {
		model.addAttribute("classroomMenu", new ClassRoomMenuDomain(option));
	}
}
