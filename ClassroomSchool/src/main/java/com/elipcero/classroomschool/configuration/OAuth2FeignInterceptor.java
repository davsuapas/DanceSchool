package com.elipcero.classroomschool.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class OAuth2FeignInterceptor implements RequestInterceptor {

    public static final String BEARER_TOKEN_TYPE = "Bearer";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {

        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)((OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication()).getDetails();

        requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,
                oAuth2AuthenticationDetails.getTokenValue()));
    }
}
