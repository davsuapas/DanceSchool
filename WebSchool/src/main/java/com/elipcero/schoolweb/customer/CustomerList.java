package com.elipcero.schoolweb.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elipcero.schoolweb.customer.domain.CustomerDescriptor;
import com.elipcero.schoolweb.customer.services.CustomerService;
import com.elipcero.schoolweb.shared.domain.DomainIterator;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;
import com.elipcero.schoolweb.shared.domain.WindowBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@Controller
@RequestMapping(value="/customer")
@AllArgsConstructor
public class CustomerList {
	
	private @NonNull final CustomerService customerService;
	
	@GetMapping(value="/list")
	public String getCustomerList(Model model) {
		FillModel(model);
		return "customer/list";
	}
	
	@PostMapping(value="/search")
	public String search(SearchForm searchForm, Model model) {
		FillModel(model, this.customerService.find(searchForm.getSecondName(), searchForm.getThirdName()), false);
		return "customer/list";
	}
	
	private static void FillModel(Model model) {
		FillModel(model, new ArrayList<CustomerDescriptor>(), true);
	}	
	
	private static void FillModel(Model model, List<CustomerDescriptor> customerDescriptors, Boolean forceSearchWindow) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Customer);
		WindowBuilder.Fill(model, forceSearchWindow);
		model.addAttribute("customersRowsCols", DomainIterator.Create(customerDescriptors, 4));
	}
}
