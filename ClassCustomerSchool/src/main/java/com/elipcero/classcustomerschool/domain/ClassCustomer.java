package com.elipcero.classcustomerschool.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassCustomer {
    private int clientId;
    private int classCalendarId;
    private int classCalendarDayId;
}
