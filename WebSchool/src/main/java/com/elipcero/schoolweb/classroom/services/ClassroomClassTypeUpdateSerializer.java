package com.elipcero.schoolweb.classroom.services;

import java.io.IOException;

import com.elipcero.schoolweb.classroom.domains.ClassroomClassType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ClassroomClassTypeUpdateSerializer extends JsonSerializer<ClassroomClassType> {

	@Override
	public void serialize(ClassroomClassType value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		gen.writeStartObject();
		gen.writeStringField("classroom", "/classrooms/" + value.getClassroom().getId().toString());
		gen.writeStringField("classType", "/classTypes/" + value.getClassType().getId().toString());
		gen.writeNumberField("classMax", value.getClassMax());
		gen.writeEndObject();
	}
}
