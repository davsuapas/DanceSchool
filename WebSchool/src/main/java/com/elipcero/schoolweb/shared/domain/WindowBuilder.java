package com.elipcero.schoolweb.shared.domain;

import org.springframework.ui.Model;

public class WindowBuilder {
	
	public static void Fill(Model model, Boolean open) {
		model.addAttribute("window", new WindowDomain(open));
	}
}
