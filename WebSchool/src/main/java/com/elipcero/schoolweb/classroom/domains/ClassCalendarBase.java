package com.elipcero.schoolweb.classroom.domains;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
public abstract class ClassCalendarBase {

    private Integer id;

    public boolean isNew() {
        return id == null;
    }

    private Classroom classroom;
    private ClassType classType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull
    private LocalTime start;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull
    private LocalTime end;
}
