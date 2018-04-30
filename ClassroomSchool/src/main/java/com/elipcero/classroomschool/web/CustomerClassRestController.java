package com.elipcero.classroomschool.web;

import com.elipcero.classroomschool.services.ClassCustomerViewResource;
import com.elipcero.classroomschool.services.CustomerClassService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CustomerClassRestController {

    @NonNull private final CustomerClassService service;
    @NonNull private final ClassCustomerViewResource classCustomerViewResource;

    @PostMapping(value = "/customerClasses")
    public ResponseEntity<?> register(@RequestBody CustomerClass customerClass) {
        if (!classCustomerViewResource.existsByCustomerIdAndClassId(customerClass.getCustomerId(), customerClass.getClassId())) {
            if (service.registers(customerClass)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
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
