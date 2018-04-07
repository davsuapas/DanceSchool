package com.elipcero.classroomschool.domains;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassCustomer {
    private int clientId;
    private String clientName;
    private int classCalendarId;
    private String classCalendarName;
    private int classCalendarDayId;
}
