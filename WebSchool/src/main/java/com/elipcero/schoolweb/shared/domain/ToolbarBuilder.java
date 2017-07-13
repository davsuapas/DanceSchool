package com.elipcero.schoolweb.shared.domain;

import org.springframework.ui.Model;

public class ToolbarBuilder {
	
	public static void Fill(Model model, ToolbarDomain.EnumMenuOption option) {
		model.addAttribute("toolbar", new ToolbarDomain(option));
	}
}