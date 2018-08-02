package com.elipcero.schoolweb.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

@RequiredArgsConstructor
public class OAuth2FeignInterceptor implements RequestInterceptor {

    public static final String BEARER_TOKEN_TYPE = "Bearer";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @NonNull private OAuth2AuthorizedClientService clientService;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(
                SecurityContextHolder.getContext().getAuthentication());

        OAuth2AuthorizedClient oAuth2AuthorizedClient = clientService.loadAuthorizedClient(
                token.getAuthorizedClientRegistrationId(),
                token.getName());

        requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,
                oAuth2AuthorizedClient.getAccessToken().getTokenValue()));
    }
}
