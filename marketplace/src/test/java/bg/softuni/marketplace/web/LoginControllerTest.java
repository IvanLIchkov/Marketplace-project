package bg.softuni.marketplace.web;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginPage() throws Exception{
       mockMvc.perform(
               MockMvcRequestBuilders.get("/users/login"))
               .andExpect(status().is2xxSuccessful())
               .andExpect(view().name("auth-login"));
    }

    @Test
    public void testFailedLogin() throws Exception{
        String username = "test@user";
        String password = "wrongPassword";
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/login-error")
                        .param(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username)
                        .param(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY, password)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"))
                .andExpect(flash().attributeExists(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY))
                .andExpect(flash().attribute("bad_credentials", true));
    }

    @Test
    public void testFailedLoginUnActiveAccount() throws Exception{
        String username = "test@user";
        String password = "password";
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/users/login-unactivated")
                                .param(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username)
                                .param(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY, password)
                                .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("activate-before-login"));
    }

}