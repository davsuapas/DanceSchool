package com.elipcero.schoolweb.classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClassRoomMenuDomain {

	public enum EnumMenuOption {
		ClassType,
	}

	private EnumMenuOption menuOptionActive;
	
	public Boolean isClassType() {
		return this.menuOptionActive == EnumMenuOption.ClassType;
	}

}
