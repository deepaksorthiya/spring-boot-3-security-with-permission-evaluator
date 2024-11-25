package com.example.web;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApi.class);

    @GetMapping("/hasPermission")
    @PreAuthorize("hasPermission('hasAccess','WRITE')")
    public ResponseEntity<User> hasPermissionOfWrite(@AuthenticationPrincipal User user) {
        LOGGER.info("Checking hasPermission('hasAccess','WRITE') :: {} ", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/preAuthWithMethodObjectArgsHasPermissionOfWrite")
    @PreAuthorize("hasPermission(#user,'WRITE')")
    public ResponseEntity<User> preAuthWithMethodObjectArgsHasPermissionOfWrite(@AuthenticationPrincipal User user) {
        LOGGER.info("Checking preAuthWithMethodObjectArgsHasPermissionOfWrite('hasAccess','WRITE') :: {} ", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/postAuthWithMethodReturnObjectArgsHasPermissionOfWrite")
    @PostAuthorize("hasPermission(returnObject.body ,'ProductOwner','READ')")
    public ResponseEntity<User> postAuthWithMethodReturnObjectArgsHasPermissionOfWrite(@AuthenticationPrincipal User user) {
        LOGGER.info("Checking postAuthWithMethodReturnObjectArgsHasPermissionOfWrite('hasAccess','WRITE') :: {} ", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/principal")
    public Principal principal(Principal principal) {
        LOGGER.info("Principal Object :: {} ", principal);
        return principal;
    }

    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
        LOGGER.info("Authentication Object :: {} ", authentication);
        return authentication;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<User> userAccess(@AuthenticationPrincipal User user) {
        LOGGER.info("You have USER level access USER :: {} ", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> adminAccess(@AuthenticationPrincipal User user) {
        LOGGER.info("You have ADMIN level access USER ::{} ", user);
        return ResponseEntity.ok(user);
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
        LOGGER.info("request headers :: {}", httpHeaders);
        return httpHeaders;
    }
}