package com.elipcero.classroomschool.domains;

import com.elipcero.schoolcore.AbstractDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ClassCalendar extends AbstractDomain {

	@ManyToOne
	private Classroom classroom;
	@ManyToOne
	private ClassType classType;

	private LocalTime start;
	private LocalTime end;
	
	@ElementCollection
	@CollectionTable(name="class_calendar_day", joinColumns=@JoinColumn(name="class_calendar_id"))
	private Set<ClassCalendarDay> classCalendarDay;

	@Transient
	public String getName() {
		return classroom.getName() + " - " + classType.getName();
	}
}
