package com.elipcero.classcustomerviewschool.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassCustomer {
    private int clientId;
    private String clientName;
    private int classCalendarId;
    private String classCalendarName;
    private int classCalendarDayId;
}
