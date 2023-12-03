package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.AppUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testIndexPageWithoutLoggedUser() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/")
        ).andExpect(status().is2xxSuccessful())
                .andExpect(view().name("index"));
    }
    @Test
    @WithUserDetails
    void testIndexPageWithLoggedUser() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/")
                ).andExpect(status().is2xxSuccessful())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("username", "categories"));
    }

}