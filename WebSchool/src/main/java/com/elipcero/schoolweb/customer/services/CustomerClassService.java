package com.elipcero.schoolweb.customer.services;

import com.elipcero.schoolweb.classroom.services.ClassCustomerResource;
import com.elipcero.schoolweb.customer.domain.CustomerClass;
import feign.FeignException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerClassService {

    @NonNull private CustomerClassResource customerClassResource;
    @NonNull private ClassCustomerResource classCustomerResource;

    public Optional<CustomerClass> getClassesByCustomerId(int id) {
        try {
            return Optional.of(customerClassResource.getClassesByCustomerId(id).getContent());
        }
        catch (FeignException ex) {
            if (ex.status() == HttpStatus.NOT_FOUND.value()) {
                return Optional.empty();
            }
            else {
                throw ex;
            }
        }
    }

    public void delete(int customerId, int classId) {
        try {
            classCustomerResource.delete(customerId, classId);
        }
        catch (FeignException ex) {
            if (ex.status() != HttpStatus.NOT_FOUND.value()) {
                throw  ex;
            }
        }
    }
}
