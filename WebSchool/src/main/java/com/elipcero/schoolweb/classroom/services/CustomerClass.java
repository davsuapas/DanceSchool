package com.elipcero.schoolweb.classroom.services;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerClass {
    private int classId;
    private int customerId;
    private String customerName;
}
