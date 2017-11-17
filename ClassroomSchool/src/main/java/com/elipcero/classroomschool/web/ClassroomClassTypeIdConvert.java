package com.elipcero.classroomschool.web;

import java.io.Serializable;

import org.springframework.data.rest.webmvc.spi.BackendIdConverter;

import com.elipcero.classroomschool.domains.ClassroomClassType;
import com.elipcero.classroomschool.domains.ClassroomClassTypeId;

public class ClassroomClassTypeIdConvert implements BackendIdConverter  {

	@Override
	public boolean supports(Class<?> type) {
		return ClassroomClassType.class.equals(type);
	}

	@Override
	public Serializable fromRequestId(String id, Class<?> entityType) {
		if (id != null) {
			String[] parts = id.split("_");
			return new ClassroomClassTypeId(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		}
		return null;
	}

	@Override
	public String toRequestId(Serializable id, Class<?> entityType) {
		ClassroomClassTypeId composeId = (ClassroomClassTypeId)id;
	    return String.format("%s_%s", composeId.getClassroom(), composeId.getClassType());
	}

}
