package com.elipcero.classroomschool.services;

import com.elipcero.classroomschool.domains.ClassCustomerDayTotal;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

@Component
public class ClassCustomerViewResourceFallback implements ClassCustomerViewResource {

    @Override
    public Resource<ClassCustomerDayTotal> getCalendarSummaryByClassId(int id) throws TimeoutException {
        throw new TimeoutException();
    }

    @Override
    public boolean existsByCustomerIdAndClassId(int customerId, int classId) {
        return false;
    }
}
