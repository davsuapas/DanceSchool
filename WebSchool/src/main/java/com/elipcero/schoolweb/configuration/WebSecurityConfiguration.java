package com.elipcero.schoolweb.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/logout", "/oauth_login").permitAll()
                .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // For demos.
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("auth_code", "JSESSIONID")
                .and()
                    .oauth2Login()
                        .loginPage("/oauth_login")
                        .userInfoEndpoint()
                            .userService(new OAuthUserService(tokenStore()));
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(this.jwtAccessTokenConverter());
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        return new OAuth2FeignInterceptor(oAuth2AuthorizedClientService);
    }
}
