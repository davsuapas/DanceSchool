package com.elipcero.classroomschool.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.DayOfWeek;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ClassCalendarDay {
	
	@Column(nullable=false)
	private DayOfWeek dayOfWeek;
}
