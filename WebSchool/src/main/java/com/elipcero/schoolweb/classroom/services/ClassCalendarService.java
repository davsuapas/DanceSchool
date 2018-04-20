package com.elipcero.schoolweb.classroom.services;

import com.elipcero.schoolcore.CollectionUtil;
import com.elipcero.schoolcore.ResourceUtil;
import com.elipcero.schoolweb.classroom.domains.ClassCalendar;
import com.elipcero.schoolweb.classroom.domains.ClassCalendarEdition;
import com.elipcero.schoolweb.classroom.domains.ClassCalendarView;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassCalendarService {
	
	private @NonNull final ClassCalendarResource classCalendarResource;

	public ClassCalendarView getClassCalendarById(int id) {
		Resource<ClassCalendarView> resource = this.classCalendarResource.getClassCalendarById(id);
		resource.getContent().setId(id);
		return resource.getContent();
	}

	public void delete(int id) {
		this.classCalendarResource.delete(id);
	}
	
	public List<ClassCalendarView> getAll() {
		return CollectionUtil.ConvertToList(this.classCalendarResource.getAll().getContent());
	}
	
	public int save(ClassCalendarEdition classCalendarEdition) {
		Resource<ClassCalendar> resource = this.classCalendarResource.post(classCalendarEdition.convertToClassCalendar());
		return ResourceUtil.GetIntegerId(resource);
	}

    public boolean IsDuplicated(ClassCalendarEdition classCalendarEdition) {
		return classCalendarResource.numberOfRecords(classCalendarEdition.getClassroom().getId(), classCalendarEdition.getClassType().getId()) > 0;
    }
}
