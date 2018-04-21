package com.elipcero.classroomschool.web;

import com.elipcero.classroomschool.services.CustomerClassService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value = "customerClasses/{customerId}/{classId}")
    public ResponseEntity<?> unRegister(@PathVariable int customerId, @PathVariable int classId) {
        if (service.unRegister(customerId, classId)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
