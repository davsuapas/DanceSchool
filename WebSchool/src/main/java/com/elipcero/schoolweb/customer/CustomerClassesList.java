package com.elipcero.schoolweb.customer;

import com.elipcero.schoolweb.customer.domain.Classes;
import com.elipcero.schoolweb.customer.domain.CustomerClass;
import com.elipcero.schoolweb.customer.services.CustomerClassService;
import com.elipcero.schoolweb.shared.domain.DomainIterator;
import com.elipcero.schoolweb.shared.domain.ToolbarBuilder;
import com.elipcero.schoolweb.shared.domain.ToolbarDomain;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/customer")
@RequiredArgsConstructor
public class CustomerClassesList {

    @NonNull public CustomerClassService service;

    @GetMapping(value="/classes/{id}")
    public String getClassesByCustomerId(@PathVariable int id, Model model) {
        FillModel(model, service.getClassesByCustomerId(id), id);
        return "customer/classes-list";
    }

    private static void FillModel(Model model, Optional<CustomerClass> customerClass, int customerId) {
        List<Classes> classes = customerClass.map(c -> c.getClasses()).orElseGet(ArrayList::new);
        ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Customer);
        model.addAttribute("classesRowsCols", DomainIterator.Create(classes, 3));
        model.addAttribute("customerId", customerId);
    }

}