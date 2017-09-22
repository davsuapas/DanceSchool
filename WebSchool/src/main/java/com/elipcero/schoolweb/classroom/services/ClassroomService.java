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
	
	private @NonNull final ClassroomResource ClassroomResource;

	public Classroom getClassroomById(Integer id) {
		Resource<Classroom> resource = this.ClassroomResource.getClassroomById(id);
		Classroom Classroom = resource.getContent();
		Classroom.setId(id);
		return Classroom; 
	}

	public void delete(Integer id) {
		this.ClassroomResource.delete(id);
	}
	
	public List<Classroom> getAll() {
		return CollectionUtil.ConvertToList(this.ClassroomResource.getAll().getContent());
	}
	
	public Integer save(Classroom classroom) {
		
		Resource<Classroom> resource = null;
		
		if (classroom.getId() == null) {
			resource = this.ClassroomResource.add(classroom);
		}
		else {
			resource = this.ClassroomResource.update(classroom.getId(), classroom);
		}
		
		return ResourceUtil.GetId(resource);
	}
}
