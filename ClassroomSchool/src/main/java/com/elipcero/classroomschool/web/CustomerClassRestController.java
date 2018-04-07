package com.elipcero.classroomschool.web;

import com.elipcero.classroomschool.services.CustomerClassService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CustomerClassRestController {

    @NonNull private final CustomerClassService service;

    @PostMapping(value = "/customerClasses")
    public ResponseEntity<?> register(@RequestBody CustomerClass customerClass) {
        if (service.registers(customerClass)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
