package com.elipcero.schoolweb.customer;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elipcero.schoolweb.customer.domain.Customer;
import com.elipcero.schoolweb.customer.services.CustomerService;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Controller
@RequestMapping(value="/customer")
@AllArgsConstructor
public class CustomerEdit {
	
	private @NonNull final CustomerService customerService;

	@GetMapping(value="/add")
	public String add(Model model) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Customer);
		model.addAttribute("customer", new Customer());
		return "customer/edit";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(Model model, @PathVariable Integer id) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Customer);
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "customer/edit";
	}
	
	@PostMapping(value="/save")
	public String save(@Valid Customer customer, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Customer);
			return "customer/edit";
		}
		
		Integer customerId = customerService.save(customer);
		
		return String.format("redirect:/customer/%s", customerId);
	}
}
