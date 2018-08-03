package com.elipcero.classcustomerviewschool.configuration;

import com.elipcero.classcustomerviewschool.domain.Classes;
import com.elipcero.classcustomerviewschool.domain.CustomerClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class DataConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(CustomerClass.class, Classes.class);
    }
}
