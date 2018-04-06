package com.elipcero.classroomschool.web;

import com.elipcero.classroomschool.domains.ClassCalendar;
import com.elipcero.classroomschool.services.ClassCalendarReaderService;
import com.elipcero.classroomschool.services.ClassCalendarService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RequiredArgsConstructor
@RepositoryRestController
public class ClassCalendarRestController {
	
	private @NonNull final ClassCalendarService serviceWriter;
	private @NonNull final ClassCalendarReaderService serviceReader;

	@GetMapping(value = "/classCalendars")
	public ResponseEntity<?> getClassCalendar() {
		return ResponseEntity.ok(new Resources<>(serviceReader.getClassCalendar()));
	}

	@PostMapping(value = "/classCalendars")
	public @ResponseBody ResponseEntity<?> mergeCalendar(@RequestBody ClassCalendar classCalendar) {
		return this.serviceWriter.upgradeCalendar(classCalendar)
			.map(result -> ResponseEntity.ok(
					new Resource<>(result, linkTo(ClassCalendarRestController.class).slash(result.getId()).withSelfRel())))
			.orElse(ResponseEntity.notFound().build());
	}
}
