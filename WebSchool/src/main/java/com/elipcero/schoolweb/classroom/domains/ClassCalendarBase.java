package com.elipcero.schoolweb.classroom.domains;

import lombok.Data;

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
    @NotNull
    private LocalTime start;
    @NotNull
    private LocalTime end;

    public String getName() {
        return this.classroom.getName() + " - " + this.classType.getName();
    }
    public String getDisplayTime() { return this.start + " - " + this.end; }
}
