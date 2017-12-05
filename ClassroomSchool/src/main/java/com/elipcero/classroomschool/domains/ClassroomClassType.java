package com.elipcero.classroomschool.domains;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClassroomClassType {
	
	private @EmbeddedId ClassroomClassTypeId id;
	
	@ManyToOne
	@MapsId("classroom")
	private Classroom classroom;
	
	@ManyToOne
	@MapsId("classType")
	private ClassType classType;
	
	@Column(nullable=false)
	private Integer classMax;	
}
