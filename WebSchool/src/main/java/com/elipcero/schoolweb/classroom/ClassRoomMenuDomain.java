package com.elipcero.schoolweb.classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClassRoomMenuDomain {

	public enum EnumMenuOption {
		ClassType,
		Classroom,
        ClassroomClassType,
		ClassCalendar
	}

	private EnumMenuOption menuOptionActive;
	
	public Boolean isClassType() {
		return this.menuOptionActive == EnumMenuOption.ClassType;
	}
	
	public Boolean isClassroom() {
		return this.menuOptionActive == EnumMenuOption.Classroom;
	}
	
	public Boolean isClassroomClassType() {
		return this.menuOptionActive == EnumMenuOption.ClassroomClassType;
	}

	public Boolean isClassCalendar() {
		return this.menuOptionActive == EnumMenuOption.ClassCalendar;
	}
}
