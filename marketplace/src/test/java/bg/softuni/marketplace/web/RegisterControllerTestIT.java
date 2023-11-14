package bg.softuni.marketplace.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.internet.MimeMessage;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;



    private GreenMail greenMail;

    @BeforeEach
    void setUp(){
        greenMail = new GreenMail(new ServerSetup(port, host, "smtp"));
        greenMail.start();
        greenMail.setUser(username, password);
    }

    @AfterEach
    void tearDown(){
        greenMail.stop();
    }


    @Test
    void testRegisterNewUser() throws Exception {
        mockMvc.perform(
        MockMvcRequestBuilders.post("/users/register")
                .param("username", "testUsername")
                .param("email", "test@mail.com")
                .param("firstName","testFirstName")
                .param("lastName", "testLastName")
                .param("password", "testPassword")
                .param("confirmPassword", "testPassword")
                .param("townName", "testTownName")
                .with(csrf())
        ).andExpect(status().is3xxRedirection())
                        .andExpect(view().name("redirect:/"));

        greenMail.waitForIncomingEmail(1);
        MimeMessage[] mimeMessages = greenMail.getReceivedMessages();

        Assertions.assertEquals(1, mimeMessages.length);
        MimeMessage registrationMessage = mimeMessages[0];

        Assertions.assertTrue(registrationMessage.getContent().toString().contains("testUsername"));
        Assertions.assertEquals(1, registrationMessage.getAllRecipients().length);
        Assertions.assertEquals("test@mail.com", registrationMessage.getAllRecipients()[0].toString());
    }
}