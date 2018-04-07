package com.elipcero.schoolweb.classroom;

import com.elipcero.schoolweb.classroom.services.ClassCalendarService;
import com.elipcero.schoolweb.classroom.services.ClassCustomerResource;
import com.elipcero.schoolweb.classroom.services.CustomerClass;
import com.elipcero.schoolweb.customer.services.CustomerService;
import com.elipcero.schoolweb.shared.web.ExceptionController;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping(value="/classcalendar")
@AllArgsConstructor
public class ClassCalendarListForSelect {

	public static final String REDIRECT_CLASSROOM_CLASSCALENDAR_LIST_FOR_SELECT = "classroom/classcalendar-list-for-select";

	private @NotNull final ClassCalendarService classCalendarService;
	private @NonNull final CustomerService customerService;
	private @NonNull final ClassCustomerResource classCustomerResource;

	@GetMapping(value="/listforselect/customer/{id}")
	public String getClassCalendarListForSelect(Model model, @PathVariable int id) {
		model.addAttribute("customerId", id);
		ClassCalendarList.FillModel(model, classCalendarService.getAll());
		return REDIRECT_CLASSROOM_CLASSCALENDAR_LIST_FOR_SELECT;
	}

	@ExceptionController(
			viewName=REDIRECT_CLASSROOM_CLASSCALENDAR_LIST_FOR_SELECT,
			messages="404;Posiblemente el cliente o la clase han sido eliminados"
	)
	@GetMapping(value="/select/class/{classId}/customer/{customerId}")
	public String select(@PathVariable int classId, @PathVariable int customerId) {
		classCustomerResource.register(
				CustomerClass.builder()
						.classId(classId)
						.customerId(customerId)
						.customerName(customerService.getCustomerById(customerId).fullName())
					.build());
		return String.format("redirect:/customer/classes/{}", customerId);
	}
}
