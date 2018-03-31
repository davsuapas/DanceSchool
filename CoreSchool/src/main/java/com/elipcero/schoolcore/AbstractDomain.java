package com.elipcero.schoolcore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

@MappedSuperclass
@Getter
@ToString
@EqualsAndHashCode
public class AbstractDomain implements Identifiable<Integer> {
	
	private @Id final @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
	private @Version Integer version;
	
	public AbstractDomain(int id) {
		this.id = id;
	}

	protected AbstractDomain() {
		this.id = null;
	}
}