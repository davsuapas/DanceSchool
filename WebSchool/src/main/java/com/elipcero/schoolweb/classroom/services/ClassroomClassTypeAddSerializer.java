package com.elipcero.schoolweb.classroom.services;

import java.io.IOException;

import com.elipcero.schoolweb.classroom.domains.ClassroomClassType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ClassroomClassTypeAddSerializer extends JsonSerializer<ClassroomClassType> {

	@Override
	public void serialize(ClassroomClassType value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		gen.writeStartObject();
		
		gen.writeFieldName("id");
		gen.writeStartObject();
		gen.writeNumberField("classroom", value.getClassroom().getId());
		gen.writeNumberField("classType", value.getClassType().getId());
		gen.writeEndObject();
		
		gen.writeNumberField("classMax", value.getClassMax());
		gen.writeEndObject();
	}
}
