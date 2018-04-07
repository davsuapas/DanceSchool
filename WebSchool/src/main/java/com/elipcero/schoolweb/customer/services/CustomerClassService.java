package com.elipcero.schoolweb.customer.services;

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

    @NonNull private CustomerClassResource resource;

    public Optional<CustomerClass> getClassesByCustomerId(int id) {
        try {
            return Optional.of(resource.getClassesByCustomerId(id).getContent());
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
}
