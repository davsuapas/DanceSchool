package com.elipcero.classcustomerviewschool.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
public class ClassCustomerDayTotal {

    public final static String CONST_FIELD_NAME_CLASSCALENDAR = "classCalendarId";
    public final static String CONST_FIELD_NAME_CUSTOMERTOTALDAY = "customerTotalDay";

    private int classCalendarId;
    private int customerTotalDay1;
    private int customerTotalDay2;
    private int customerTotalDay3;
    private int customerTotalDay4;
    private int customerTotalDay5;
    private int customerTotalDay6;
    private int customerTotalDay7;
}
