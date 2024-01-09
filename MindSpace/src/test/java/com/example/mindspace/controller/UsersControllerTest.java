package com.example.mindspace.controller;

import static org.mockito.Mockito.when;

import com.example.mindspace.api.AdminResponse;
import com.example.mindspace.security.UserPrincipal;
import com.example.mindspace.service.impl.UserServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UsersController.class})
@ExtendWith(SpringExtension.class)
class UsersControllerTest {
    @MockBean
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UsersController usersController;

    /**
     * Method under test: {@link UsersController#getCurrentUser(UserPrincipal)}
     */
    @Test
    void testGetCurrentUser() throws Exception {
        // Arrange
        when(userServiceImpl.getCurrentUser(Mockito.<UserPrincipal>any()))
                .thenReturn(new AdminResponse(1, "Name", true, true));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/profile");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("userPrincipal",
                String.valueOf(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>())));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"name\":\"Name\",\"isAdmin\":true,\"finishedRegistration\":true}"));
    }

    /**
     * Method under test: {@link UsersController#getCurrentUser(UserPrincipal)}
     */
    @Test
    void testGetCurrentUser2() throws Exception {
        // Arrange
        when(userServiceImpl.getCurrentUser(Mockito.<UserPrincipal>any()))
                .thenReturn(new AdminResponse(1, "Name", true, true));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/profile");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("userPrincipal", String.valueOf(""));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"name\":\"Name\",\"isAdmin\":true,\"finishedRegistration\":true}"));
    }
}
