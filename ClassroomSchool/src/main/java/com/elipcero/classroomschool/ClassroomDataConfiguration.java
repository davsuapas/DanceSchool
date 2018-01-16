package com.elipcero.classroomschool;

import com.elipcero.classroomschool.domains.ClassCalendar;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.elipcero.classroomschool.domains.ClassType;
import com.elipcero.classroomschool.domains.Classroom;
import com.elipcero.classroomschool.repositories.ClassTypeRepository;
import com.elipcero.classroomschool.repositories.ClassroomClassTypeEventHandler;
import com.elipcero.classroomschool.repositories.ClassroomRepository;
import com.elipcero.classroomschool.web.ClassroomClassTypeIdConvert;

@Configuration
@EnableJpaRepositories("com.elipcero.classroomschool.repositories")
@EntityScan(basePackageClasses = {Application.class, Jsr310JpaConverters.class})
public class ClassroomDataConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(ClassType.class, Classroom.class, ClassCalendar.class);
    }
    
    @Bean
    public ClassroomClassTypeIdConvert classroomClassTypeIdConvert() {
    	return new ClassroomClassTypeIdConvert();
    }
    
	@Autowired private ClassroomRepository classroomRepository;
	@Autowired private ClassTypeRepository classTypeRepository;
    
    @Bean
    ClassroomClassTypeEventHandler classroomClassTypeEventHandler() {
    	return new ClassroomClassTypeEventHandler(classroomRepository, classTypeRepository);
    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
    }
}
