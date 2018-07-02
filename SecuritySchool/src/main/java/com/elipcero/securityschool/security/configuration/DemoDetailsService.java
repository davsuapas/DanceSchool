package com.elipcero.securityschool.security.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DemoDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final Map<String, List<GrantedAuthority>> usersInfo = new HashMap<>();

    DemoDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        usersInfo.put("admin", AuthorityUtils.createAuthorityList("ADMIN", "USER"));
        usersInfo.put("user", AuthorityUtils.createAuthorityList("ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String passwordEncode = passwordEncoder.encode("password");
        List<GrantedAuthority> auth = usersInfo.get(username);
        return new User(username, passwordEncode, true, true, true, true, auth);
    }
}

