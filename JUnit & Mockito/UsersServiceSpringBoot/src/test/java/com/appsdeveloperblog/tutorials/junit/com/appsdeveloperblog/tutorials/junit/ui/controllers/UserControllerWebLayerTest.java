package com.appsdeveloperblog.tutorials.junit.com.appsdeveloperblog.tutorials.junit.ui.controllers;

//  INTEGRATION TEST

import com.appsdeveloperblog.tutorials.junit.service.UsersService;
import com.appsdeveloperblog.tutorials.junit.shared.UserDto;
import com.appsdeveloperblog.tutorials.junit.ui.controllers.UsersController;
import com.appsdeveloperblog.tutorials.junit.ui.request.UserDetailsRequestModel;
import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = UsersController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
// to tell spring framework, that i want to create application context only for those beans that are related to web layer,
// we annotate it with @WebMvcTest

// As we are testing web layer only we are not interested in security filters
// we can exclude them with the below annotation,
// or we can add it to above @WebMvcTest too, with excludeAutoConfiguration = {SecurityAutoConfiguration.class}
// so comment the below annotation
//@AutoConfigureMockMvc(addFilters = false)

public class UserControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UsersService usersService;

    UserDetailsRequestModel userDetailsRequestModel;

    @BeforeEach
    void init(){
        userDetailsRequestModel = new UserDetailsRequestModel();
        userDetailsRequestModel.setFirstName("Shahrukh");
        userDetailsRequestModel.setLastName("Tramboo");
        userDetailsRequestModel.setEmail("tramboos123@gmail.com");
        userDetailsRequestModel.setPassword("shahrukh");
        userDetailsRequestModel.setRepeatPassword("shahrukh");
    }

    @Test
    @DisplayName("User can be created")
    void testCreateUser_whenValidUserDetailsProvided_returnsCreatedUserDetails()
            throws Exception {
//        Arrange

//        UserDto userDto = new UserDto();
//        userDto.setFirstName("Shahrukh");
//        userDto.setLastName("Tramboo");
//        userDto.setEmail("tramboos123@gmail.com");
//        userDto.setUserId(UUID.randomUUID().toString());

        UserDto userDto =  new ModelMapper().map(userDetailsRequestModel,UserDto.class);
        userDto.setUserId(UUID.randomUUID().toString());
        when(usersService.createUser(any(UserDto.class))).thenReturn(userDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

//        Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        UserRest createdUser = new ObjectMapper().readValue(responseBodyAsString, UserRest.class);


//        Assert
        assertEquals(userDetailsRequestModel.getFirstName(),
                createdUser.getFirstName(),
                "The returned user first name is most likely incorrect");

        assertEquals(userDetailsRequestModel.getLastName(),
                createdUser.getLastName(),
                "The returned user last name is most likely incorrect");

        assertEquals(userDetailsRequestModel.getEmail(),
                createdUser.getEmail(),
                "The returned user email is most likely incorrect");

        assertFalse(createdUser.getUserId().isEmpty(), "userId should not be empty");


    }

    @Test
    @DisplayName("First name is not empty")
    void testCreateUser_whenFirstNameIsNotProvided_return400StatusCode() throws Exception {
//        Arrange
        UserDetailsRequestModel userDetailsRequestModel = new UserDetailsRequestModel();
        userDetailsRequestModel.setFirstName("");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

//        Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

//        Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");

    }

    @Test
    @DisplayName("First name cannot be shorter than 2 characters")
    void testCreateUser_whenFirstNameIsOnlyOneCharacter_returns400StatusCode() throws Exception{
//        Arrange
        userDetailsRequestModel.setFirstName("a");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

//        Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

//        Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");

    }
}
