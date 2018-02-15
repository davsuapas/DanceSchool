package com.elipcero.classcustomerschool.web;

import com.elipcero.classcustomerschool.domain.ClassCustomerEvent;
import com.elipcero.classcustomerschool.message.ClassCustomerMessage;
import com.elipcero.classcustomerschool.message.Converter;
import com.elipcero.classcustomerschool.repository.ClassCustomerEventRepository;
import com.elipcero.classcustomerschool.service.CounterService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController()
@RequestMapping(value = "classcustomers")
public class ClassCustomerEventController {

    @NonNull private final ClassCustomerEventRepository repository;
    @NonNull private final CounterService counterService;

    @PostMapping(value = "/register")
    public @ResponseBody ResponseEntity<?> register(@RequestBody ClassCustomerMessage classCustomerEvent) {
        return ResponseEntity.ok(repository.save(
                Converter.convertToClassCustomerEvent(this.counterService.count(), classCustomerEvent)));
    }

    @PostMapping(value = "/registers")
    public @ResponseBody ResponseEntity<?> registers(@RequestBody List<ClassCustomerMessage> classCustomerMessages) {
        List<ClassCustomerEvent> classCustomers = new ArrayList<>();

        for (ClassCustomerMessage classCustomerMessage : classCustomerMessages) {
            classCustomers.add(Converter.convertToClassCustomerEvent(this.counterService.count(), classCustomerMessage));
        }

        return ResponseEntity.ok(repository.save(classCustomers));
    }
}
