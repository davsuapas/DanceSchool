package com.elipcero.classcustomerviewschool.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "ClassCustomerDayTotal")
public class ClassCustomerDayTotal {

    public final static String CONST_FIELD_NAME_CLASSCALENDARID = "_id";
    public final static String CONST_FIELD_NAME_CUSTOMERTOTALDAY = "customerTotalDay";
    public final static String CONST_FIELD_NAME_EVENTTRANSACTION = "eventTransaction";

    @Id
    private int id;

    private int customerTotalDay1;
    private int customerTotalDay2;
    private int customerTotalDay3;
    private int customerTotalDay4;
    private int customerTotalDay5;
    private int customerTotalDay6;
    private int customerTotalDay7;
}

