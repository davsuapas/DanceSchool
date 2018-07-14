package com.elipcero.securityschool.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //@formatter:off
        http
            .requestMatchers() // Only requests login matching are handled by this security configurer
                .antMatchers("/login/**", "/oauth/**")
            .and()
                .authorizeRequests()
                    .antMatchers("/login/login*").permitAll()
                    .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/login/login.html")
                    .loginProcessingUrl("/login/loginProcess")
                    .failureUrl("/login/login.html?error=true");
        //@formatter:on
    }


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
