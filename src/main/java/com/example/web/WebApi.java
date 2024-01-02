package com.example.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebApi {

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public String userAccess(@AuthenticationPrincipal User user) {
        return "You have USER level access USERNAME : " + user.getUsername();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminAccess(@AuthenticationPrincipal User user) {
        return "You have ADMIN level access USERNAME : " + user.getUsername();
    }

    @GetMapping("/hasPermission")
    @PreAuthorize("hasPermission('hasAccess','WRITE')")
    public User getOAuth2AccessToken(@AuthenticationPrincipal User user) {
        return user;
    }
}