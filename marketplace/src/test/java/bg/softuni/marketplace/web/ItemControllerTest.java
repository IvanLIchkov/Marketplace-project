package bg.softuni.marketplace.web;

import bg.softuni.marketplace.model.domain.ItemEntity;
import bg.softuni.marketplace.model.domain.UserEntity;
import bg.softuni.marketplace.testutils.TestDataUtil;
import bg.softuni.marketplace.testutils.UserTestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

//    @BeforeEach
//    void setUp() {
//        testDataUtil.cleanUp();
//        userTestDataUtil.cleanUp();
//    }
//
//    @AfterEach
//    void tearDown() {
//        testDataUtil.cleanUp();
//        userTestDataUtil.cleanUp();
//    }


    @Test
    void testAnonymousDeletionFails() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser("username@test");
        ItemEntity itemEntity = testDataUtil.createItemTest(owner);

        mockMvc.perform(
                        delete("/delete/{id}", itemEntity.getId())
                                .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("redirect:/"));


    }

    @Test
    @WithMockUser(username = TEST_USER1_USERNAME)
    void testNonAdminUserOwnedOffer() throws Exception {
        UserEntity owner = userTestDataUtil.createTestUser(TEST_USER1_USERNAME);
        ItemEntity offerEntity = testDataUtil.createItemTest(owner);

        mockMvc.perform(
                        delete("/delete/{id}", offerEntity.getId())
                                .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }


}
