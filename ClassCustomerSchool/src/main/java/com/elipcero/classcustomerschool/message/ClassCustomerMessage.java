package com.elipcero.classcustomerschool.message;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassCustomerMessage {

    private String eventType;
    private int clientId;
    private int classCalendarId;
    private int classCalendarDayId;
}
