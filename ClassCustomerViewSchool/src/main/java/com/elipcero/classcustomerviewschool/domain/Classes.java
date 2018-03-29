package com.elipcero.classcustomerviewschool.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Builder
public class Classes {

    public static final String CONST_FIELD_NAME_DAY = "day";
    public static final String CONST_FIELD_NAME_ID = "_id";
    public static final String CONST_FIELD_NAME_NAME = "name";

    private int id;

    private String name;
    private List<DayOfWeek> day;
}
