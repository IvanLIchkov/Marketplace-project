package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.repository.BlackListedRepository;
import bg.softuni.marketplace.service.UserService;
import bg.softuni.marketplace.service.impl.BlackListService;
import bg.softuni.marketplace.testutils.UserTestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BlackListedRepository blackListedRepository;

    @Mock
    private BlackListService blackListService;


    @Mock
    private UserService userService;

    @Autowired
    private UserTestDataUtil userTestDataUtil;

    @BeforeEach
    void setUp() {
        this.blackListService = new BlackListService(blackListedRepository);
    }

    @Test
    @WithMockUser(username = "testAdmin", roles = "ADMIN")
    void testAdminManagePage() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/manage")
                        .with(csrf())
        ).andExpect(MockMvcResultMatchers.view().name("manage-users-admin"));
    }

    @Test
    @WithMockUser(username = "testAdmin", roles = "ADMIN")
    void banUserTest() throws Exception {
        UserEntity testUser = userTestDataUtil.createTestUser("testUser");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/ban/{userId}", testUser.getId())
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }
    @Test
    @WithMockUser(username = "testAdmin", roles = "ADMIN")
    void unbanUser() throws Exception {
        UserEntity testUser = userTestDataUtil.createTestUser("testUser");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/unban/{userId}", testUser.getId())
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "testAdmin", roles = "ADMIN")
    void promoteToAdmin() throws Exception {
        UserEntity testUser = userTestDataUtil.createTestUser("testUser");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/promote/{userId}", testUser.getId())
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }
    @Test
    @WithMockUser(username = "testAdmin", roles = "ADMIN")
    void getUserDetailsViewTest() throws Exception {
        UserEntity testUser = userTestDataUtil.createTestUser("testUser");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/view/details/{userId}", testUser.getId())
                        .with(csrf())
        ).andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user-view"));
    }
}