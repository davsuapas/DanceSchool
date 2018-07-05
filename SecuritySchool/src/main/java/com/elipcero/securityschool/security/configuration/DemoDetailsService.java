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

    private final PasswordEncoder passwordEncoder;
    private final Map<String, User> usersInfo = new HashMap<>();

    DemoDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        usersInfo.put("admin", new User("admin", "password", true, true, true, true, AuthorityUtils.createAuthorityList("ADMIN", "USER")));
        usersInfo.put("user", new User("user", "password", true, true, true, true, AuthorityUtils.createAuthorityList("USER")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //String passwordEncode = passwordEncoder.encode("password");
        return usersInfo.get(username);
    }
}

