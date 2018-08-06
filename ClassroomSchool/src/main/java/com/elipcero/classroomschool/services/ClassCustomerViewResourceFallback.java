package com.elipcero.classroomschool.services;

import com.elipcero.classroomschool.domains.ClassCustomerDayTotal;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

@Component
public class ClassCustomerViewResourceFallback implements ClassCustomerViewResource {

    @Override
    public Resource<ClassCustomerDayTotal> getCalendarSummaryByClassId(int id) {
        return null;
    }

    @Override
    public boolean existsByCustomerIdAndClassId(int customerId, int classId) {
        return false;
    }
}
