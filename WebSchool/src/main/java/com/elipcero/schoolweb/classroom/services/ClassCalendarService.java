package com.elipcero.schoolweb.classroom.services;

import com.elipcero.schoolcore.CollectionUtil;
import com.elipcero.schoolcore.ResourceUtil;
import com.elipcero.schoolweb.classroom.domains.ClassCalendar;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassCalendarService {
	
	private @NonNull final ClassCalendarResource classCalendarResource;

	public ClassCalendar getClassCalendarById(Integer id) {
		Resource<ClassCalendar> resource = this.classCalendarResource.getClassCalendarById(id);
		ClassCalendar classCalendar = resource.getContent();
		classCalendar.setId(id);
		return classCalendar;
	}

	public void delete(int id) {
		this.classCalendarResource.delete(id);
	}
	
	public List<ClassCalendar> getAll() {
		return CollectionUtil.ConvertToList(this.classCalendarResource.getAll().getContent());
	}
	
	public int save(ClassCalendar classCalendar) {
		Resource<ClassCalendar> resource = this.classCalendarResource.post(classCalendar);
		return ResourceUtil.GetIntegerId(resource);
	}
}
