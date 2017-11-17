package com.elipcero.schoolweb.classroom.services;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import com.elipcero.schoolcore.CollectionUtil;
import com.elipcero.schoolcore.ResourceUtil;
import com.elipcero.schoolweb.classroom.domains.ClassType;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class ClassTypeService {
	
	private @NonNull final ClassTypeResource classTypeResource;

	public ClassType getClassTypeById(Integer id) {
		Resource<ClassType> resource = this.classTypeResource.getClassTypeById(id);
		ClassType classType = resource.getContent();
		classType.setId(id);
		return classType; 
	}

	public void delete(Integer id) {
		this.classTypeResource.delete(id);
	}
	
	public List<ClassType> getAll() {
		return CollectionUtil.ConvertToList(this.classTypeResource.getAll().getContent());
	}
	
	public Integer save(ClassType classType) {
		
		Resource<ClassType> resource = null;
		
		if (classType.getId() == null) {
			resource = this.classTypeResource.add(classType);
		}
		else {
			resource = this.classTypeResource.update(classType.getId(), classType);
		}
		
		return ResourceUtil.GetIntegerId(resource);
	}
}
