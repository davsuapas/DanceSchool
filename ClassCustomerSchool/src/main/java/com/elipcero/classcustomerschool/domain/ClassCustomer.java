package com.elipcero.classcustomerschool.domain;

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
    private int classCalendarId;
    private int classCalendarDayId;
}
