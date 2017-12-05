package com.elipcero.schoolweb.classroom.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Data
@NoArgsConstructor
public class ClassCalendarDay {

    private DayOfWeek dayOfWeek;
    private Integer classPeople;

    public String getFirstLetterOfDay() {
        return this.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.getDefault());
    }
}
