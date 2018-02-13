package com.elipcero.classcustomerschool.web;

import com.elipcero.classcustomerschool.domain.ClassCustomer;
import com.elipcero.classcustomerschool.domain.Event;
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
    public @ResponseBody ResponseEntity<?> register(@RequestBody ClassCustomerEvent classCustomerEvent) {
        return ResponseEntity.ok(repository.save(classCustomerEvent.convertToEvent(this.counterService.count())));
    }

    @PostMapping(value = "/registers")
    public @ResponseBody ResponseEntity<?> registers(@RequestBody List<ClassCustomerEvent> classCustomerEvents) {
        List<Event<ClassCustomer>> classCustomers = new ArrayList<>();

        for (ClassCustomerEvent classCustomerEvent : classCustomerEvents) {
            classCustomers.add(classCustomerEvent.convertToEvent(this.counterService.count()));
        }

        return ResponseEntity.ok(repository.save(classCustomers));
    }
}
