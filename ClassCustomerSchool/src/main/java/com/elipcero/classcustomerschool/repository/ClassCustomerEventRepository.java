package com.elipcero.classcustomerschool.repository;

import com.elipcero.classcustomerschool.domain.ClassCustomer;
import com.elipcero.classcustomerschool.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface ClassCustomerEventRepository extends CrudRepository<Event<ClassCustomer>, Long> {
}
