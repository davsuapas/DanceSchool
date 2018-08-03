package com.elipcero.schoolweb.configuration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.*;

@RequiredArgsConstructor
public class OAuthUserService implements org.springframework.security.oauth2.client.userinfo.OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    public static final String USER_NAME = "user_name";

    private final @NonNull TokenStore tokenStore;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2AccessToken oAuth2AccessToken = this.tokenStore.readAccessToken(oAuth2UserRequest.getAccessToken().getTokenValue());

        Set<GrantedAuthority> grantedAuthorities = new HashSet();
        ArrayList<String> authorities = (ArrayList<String>)oAuth2AccessToken.getAdditionalInformation().get("authorities");
        String userName = (String)oAuth2AccessToken.getAdditionalInformation().get(USER_NAME);
        Map<String, Object> userAttributes = Collections.singletonMap(USER_NAME, userName);
        authorities.forEach(role -> grantedAuthorities.add(new OAuth2UserAuthority(role, userAttributes)));

        return new DefaultOAuth2User(grantedAuthorities, Collections.singletonMap(USER_NAME, userName), USER_NAME);
    }
}
