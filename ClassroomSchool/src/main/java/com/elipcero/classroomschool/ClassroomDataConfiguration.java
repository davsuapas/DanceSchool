package com.elipcero.classroomschool;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.elipcero.classroomschool.domains.ClassType;
import com.elipcero.classroomschool.domains.Classroom;

@Configuration
@EnableJpaRepositories("com.elipcero.classroomschool.repositories")
public class ClassroomDataConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(ClassType.class, Classroom.class);
    }
}
