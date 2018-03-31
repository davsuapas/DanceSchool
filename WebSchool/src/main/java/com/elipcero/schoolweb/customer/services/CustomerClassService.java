package com.elipcero.schoolweb.customer.services;

import com.elipcero.schoolweb.customer.domain.CustomerClass;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerClassService {

    @NonNull private CustomerClassResource resource;

    public Optional<CustomerClass> getClassesByCustomerId(int id) {
        return Optional.ofNullable(resource.getClassesByCustomerId(id).getContent());
    }
}
