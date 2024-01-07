package com.example.mindspace.controller;

import static org.mockito.Mockito.doNothing;

import com.example.mindspace.service.impl.AdminServiceImpl;
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

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerDiffblueTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminServiceImpl adminServiceImpl;

    /**
     * Method under test: {@link AdminController#approveTherapist(Integer)}
     */
    @Test
    void testApproveTherapist() throws Exception {
        // Arrange
        doNothing().when(adminServiceImpl).approveTherapist(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/admin/approve/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AdminController#approveTherapist(Integer)}
     */
    @Test
    void testApproveTherapist2() throws Exception {
        // Arrange
        doNothing().when(adminServiceImpl).approveTherapist(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/admin/approve/{id}", 1);
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
