package com.elipcero.securityschool.security.configuration;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoDetailsService implements UserDetailsService {

    private final Map<String, User> usersInfo = new HashMap<>();

    DemoDetailsService(PasswordEncoder passwordEncoder) {
        String pass = passwordEncoder.encode("password");
        usersInfo.put("admin", new User("admin", pass, true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")));
        usersInfo.put("user", new User("user", pass, true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_USER")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //String passwordEncode = passwordEncoder.encode("password");
        return usersInfo.get(username);
    }
}

