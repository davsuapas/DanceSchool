package com.elipcero.classroomschool.web;

import com.elipcero.classroomschool.domains.ClassCalendar;
import com.elipcero.classroomschool.services.ClassCalendarService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RequiredArgsConstructor
@RepositoryRestController
public class ClassCalendarRestController {
	
	private @NonNull final ClassCalendarService service;

	@GetMapping(value = "/classCalendars")
	public List<ClassCalendar> getClassCalendar() {
		return service.getClassCalendar();
	}

	@PostMapping(value = "/classCalendars")
	public @ResponseBody ResponseEntity<?> mergeCalendar(@RequestBody ClassCalendar classCalendar) {
		return this.service.upgradeCalendar(classCalendar)
			.map(result -> ResponseEntity.ok(
					new Resource<>(result, linkTo(ClassCalendarRestController.class).slash(result.getId()).withSelfRel())))
			.orElse(ResponseEntity.notFound().build());
	}
}
