package com.elipcero.schoolweb.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/customer/selection")
@AllArgsConstructor
public class CustomerClassSelection {

    @GetMapping(value="/list")
    public String getClassSelectionList(Model model) {
        return "customer/list";
    }
}
