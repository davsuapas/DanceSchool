package com.elipcero.classroomschool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
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
public class ClassroomDataConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(ClassType.class, Classroom.class);
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
}
