package com.elipcero.schoolweb.classroom.services;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import com.elipcero.schoolcore.CollectionUtil;
import com.elipcero.schoolcore.ResourceUtil;
import com.elipcero.schoolweb.classroom.domains.ClassroomClassType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class ClassroomClassTypeService {

	private @NonNull final ClassroomClassTypeResource resource;
	
	public List<ClassroomClassType> getAll() {
		return CollectionUtil.ConvertToList(this.resource.getAll().getContent());
	}
	
	public ClassroomClassType getById(String id) {
		Resource<ClassroomClassType> resource = this.resource.getClassroomClassTypeById(id);
		ClassroomClassType classroomClassType = resource.getContent();
		return classroomClassType; 
	}
	
	public void delete(String id) {
		this.resource.delete(id);
	}
	
	public String save(String mode, ClassroomClassType classroomClassType) throws JsonProcessingException {
		
		Resource<ClassroomClassType> resource = null;
		
		ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("ClassroomClassType Module");	
		
		if (mode.equals("adding")) {
			module.addSerializer(ClassroomClassType.class, new ClassroomClassTypeAddSerializer());	
	        mapper.registerModule(module);
        	resource = this.resource.add(mapper.writeValueAsString(classroomClassType));
		}
		else {
			module.addSerializer(ClassroomClassType.class, new ClassroomClassTypeUpdateSerializer());	
	        mapper.registerModule(module);			
			resource = this.resource.update(classroomClassType.getId(), mapper.writeValueAsString(classroomClassType));
		}
		
		return ResourceUtil.GetStringId(resource);
	}
}