package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.AppUserDetails;
import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.model.dto.AddItemDto;
import bg.softuni.marketplace.repository.UserRepository;
import bg.softuni.marketplace.service.ItemService;
import bg.softuni.marketplace.service.impl.ApplicationUserDetailsService;
import bg.softuni.marketplace.testutils.TestDataUtil;
import bg.softuni.marketplace.testutils.UserTestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Collections;
import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    private static final String TEST_USER1_USERNAME = "user1@test";
    private static final String TEST_USER2_USERNAME = "user2@test";
    private static final String TEST_ADMIN_USERNAME= "admin@test";

    @Autowired
    private TestDataUtil testDataUtil;

    @Autowired
    private UserTestDataUtil userTestDataUtil;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Mock
    private ItemService itemService;


    @Test
    @WithUserDetails
    void addProductSuccess() throws Exception {
        MockMultipartFile testFile = new MockMultipartFile("img", "testImage.txt", "text/plain", "some img type".getBytes());

        mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/items/add")
                                .file(testFile)
                                .param("name", "testName")
                                .param("price", "100")
                                .param("description", "Test description")
                                .param("categoryId", "1")
                                .with(csrf())

                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
   @WithUserDetails
    void addProductFail() throws Exception {
        MockMultipartFile testFile = new MockMultipartFile("data", "testImage.txt","text/plain","some img type".getBytes());

        mockMvc.perform(
                MockMvcRequestBuilders.multipart("/items/add")
                        .file(testFile)
                        .param("name", "testName")
                        .param("img", "testImage.txt")
                        .param("price", "100")
                        .param("description", "Test description")
                        .param("categoryId", "1")
                        .with(csrf())

        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/items/add"));
    }

    @Test
    void testAnonymousDeletionFails() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser("test@example.com");
        ItemEntity offerEntity = testDataUtil.createItemTest(owner);

        mockMvc.perform(
                        delete("/items/delete/{id}", offerEntity.getId())
                                .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"));
    }

    @Test
    @WithMockUser(username = TEST_USER1_USERNAME)
    void testNonAdminUserOwnedOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER1_USERNAME);
        ItemEntity itemEntity = testDataUtil.createItemTest(owner);

        mockMvc.perform(
                        delete("/items/delete/{id}", itemEntity.getId())
                                .with(csrf())
                ).andExpect(status().is3xxRedirection());
    }


    private UserDetails mockUserDetails() {
        return new AppUserDetails(TEST_USER1_USERNAME, "password", true, true, true, true, Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }

}
