package com.elipcero.schoolweb.classroom.services;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import com.elipcero.schoolcore.CollectionUtil;
import com.elipcero.schoolcore.ResourceUtil;
import com.elipcero.schoolweb.classroom.domains.Classroom;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class ClassroomService {
	
	private @NonNull final ClassroomResource classroomResource;

	public Classroom getClassroomById(Integer id) {
		Resource<Classroom> resource = this.classroomResource.getClassroomById(id);
		Classroom classroom = resource.getContent();
		classroom.setId(id);
		return classroom; 
	}

	public void delete(Integer id) {
		this.classroomResource.delete(id);
	}
	
	public List<Classroom> getAll() {
		return CollectionUtil.ConvertToList(this.classroomResource.getAll().getContent());
	}
	
	public Integer save(Classroom classroom) {
		
		Resource<Classroom> resource = null;
		
		if (classroom.getId() == null) {
			resource = this.classroomResource.add(classroom);
		}
		else {
			resource = this.classroomResource.update(classroom.getId(), classroom);
		}
		
		return ResourceUtil.GetIntegerId(resource);
	}
}
