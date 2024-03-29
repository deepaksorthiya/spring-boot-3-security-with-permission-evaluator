package com.example.web;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.Map;

@RestController
public class WebApi {

    private final Logger logger = LoggerFactory.getLogger(WebApi.class);

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<User> userAccess(@AuthenticationPrincipal User user) {
        logger.info("You have USER level access USER :: {} ", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> adminAccess(@AuthenticationPrincipal User user) {
        logger.info("You have ADMIN level access USER ::{} ", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/hasPermission")
    @PreAuthorize("hasPermission('hasAccess','WRITE')")
    public ResponseEntity<User> hasPermissionOfWrite(@AuthenticationPrincipal User user) {
        logger.info("Checking hasPermission('hasAccess','WRITE') :: {} ", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/principal")
    public Principal principal(Principal principal) {
        logger.info("Principal Object :: {} ", principal);
        return principal;
    }

    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
        logger.info("Authentication Object :: {} ", authentication);
        return authentication;
    }

    @GetMapping(value = "/server-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getRequestInfo(@RequestHeader Map<String, String> httpHeaders, HttpServletRequest httpServletRequest) {
        httpHeaders.put("remoteHost", httpServletRequest.getRemoteHost());
        httpHeaders.put("localAddress", httpServletRequest.getLocalAddr());
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            httpHeaders.put("hostName", localHost.getHostName());
            httpHeaders.put("hostAddress", localHost.getHostAddress());
            httpHeaders.put("canonicalHostName", localHost.getCanonicalHostName());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        logger.info("request headers :: {}", httpHeaders);
        return httpHeaders;
    }
}