package com.elipcero.schoolweb.customer.domain;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
public class Classes {

    private int id;

    private String name;
    private List<DayOfWeek> day;

    public String getDaysOfWeek() {
        return day.stream()
                .map(d -> d.getDisplayName(TextStyle.SHORT, Locale.getDefault()))
                .collect(Collectors.joining(" "));
    }
}
