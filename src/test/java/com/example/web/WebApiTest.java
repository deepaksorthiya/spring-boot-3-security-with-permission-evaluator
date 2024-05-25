package com.example.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.FormLoginRequestBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("user")
                .password("password");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername("user"));
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        FormLoginRequestBuilder login = formLogin()
                .user("invalid")
                .password("invalidpassword");

        mockMvc.perform(login)
                .andExpect(unauthenticated());
    }

    @Test
    public void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).contains("user");
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN", "ROLE_ADMIN"})
    public void accessSecuredResourceWithAdminThenOk() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).contains("admin");
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN", "ROLE_USER"})
    public void accessSecuredResourceWithAdminHasPermissionThenOk() throws Exception {
        mockMvc.perform(get("/hasPermission"))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN", "ROLE_USER"})
    public void accessSecuredResourceWithAdminPrincipalThenOk() throws Exception {
        mockMvc.perform(get("/principal"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN", "ROLE_USER"})
    public void accessSecuredResourceWithAdminAuthenticationThenOk() throws Exception {
        mockMvc.perform(get("/authentication"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN", "ROLE_USER"})
    public void accessSecuredResourceWithAdminAuthenticationApiPreAuthWithMethodObjectArgsHasPermissionOfWriteThenOk() throws Exception {
        mockMvc.perform(get("/preAuthWithMethodObjectArgsHasPermissionOfWrite"))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", authorities = {"ROLE_ADMIN", "ROLE_USER"})
    public void accessSecuredResourceWithAdminAuthenticationApiPostAuthWithMethodObjectArgsHasPermissionOfWriteThenOk() throws Exception {
        mockMvc.perform(get("/postAuthWithMethodReturnObjectArgsHasPermissionOfWrite"))
                .andExpect(status().isForbidden())
                .andReturn();
    }
}