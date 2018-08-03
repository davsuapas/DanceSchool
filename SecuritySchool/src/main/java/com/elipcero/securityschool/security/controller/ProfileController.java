package com.elipcero.securityschool.security.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
class ProfileController {

    @GetMapping("/resources/userinfo")
    Map<String, String> profile(Principal principal) {
        return Collections.singletonMap("name", principal.getName());
    }
}