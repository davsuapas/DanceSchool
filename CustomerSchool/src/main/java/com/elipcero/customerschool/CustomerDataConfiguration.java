package com.elipcero.customerschool;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.elipcero.customerschool.repositories")
public class CustomerDataConfiguration {
}
