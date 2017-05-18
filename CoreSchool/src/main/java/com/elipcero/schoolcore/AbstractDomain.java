package com.elipcero.schoolcore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.hateoas.Identifiable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@MappedSuperclass
@Getter
@ToString
@EqualsAndHashCode
public class AbstractDomain implements Identifiable<Integer> {

	private final @Id @GeneratedValue(strategy = GenerationType.AUTO) Integer id;
	private @Version Integer version;

	protected AbstractDomain() {
		this.id = null;
	}
}