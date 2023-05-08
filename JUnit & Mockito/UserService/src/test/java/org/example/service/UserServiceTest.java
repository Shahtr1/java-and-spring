package org.example.service;

import org.example.data.UsersRepository;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    // this will make mockito create a new instance of UserServiceImpl class and inject UsersRepository dependency in it
    UserServiceImpl userService;

    @Mock
    UsersRepository usersRepository;

    @Mock
    EmailVerificationServiceImpl emailVerificationService;

    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void init(){
         firstName = "Shahrukh";
         lastName = "Tramboo";
         email = "tramboos123@gmail.com";
         password = "shahrukh";
         repeatPassword = "shahrukh";
    }

    @DisplayName("User object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnUserObject(){
//        Arrange
        // when a save method is called, then do something
        when(usersRepository.save(any(User.class))).thenReturn(true);

//        Act
        User user = userService.createUser(firstName,lastName,email,password,repeatPassword);


//        Assert
        assertNotNull(user,"The createUser() should not have returned null");
        assertEquals(firstName, user.getFirstName(),"User's first name is incorrect");
        assertEquals(lastName, user.getLastName(),"User's last name is incorrect");
        assertEquals(email, user.getEmail(),"User's email is incorrect");
        assertNotNull(user.getId(),"User id is missing");
//        verify(usersRepository, times(1)).save(any(User.class));
        verify(usersRepository).save(any(User.class));
    }

    @DisplayName("Empty first name causes correct exception")
    @Test
    void testCreateUser_whenFirstnameIsEmpty_throwsIllegalArgumentException(){
//       Arrange
        String firstName = "";
        String expectedExceptionMessage = "User's first name is empty";


//        Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,()->{
            userService.createUser(firstName,lastName,email,password,repeatPassword);
        },"Empty first name should have thrown an Illegal Argument Exception");

//        Assert
        assertEquals(expectedExceptionMessage,thrown.getMessage(),
                "Exception error message is not correct");

    }

    @DisplayName("If save() method causes RuntimeException, a UserServiceException is thrown")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException(){
//        Arrange
        when(usersRepository.save(any(User.class))).thenThrow(RuntimeException.class);

//        Act
        assertThrows(UserServiceException.class, ()->{
            userService.createUser(firstName,lastName,email,password,repeatPassword);
        }, "Should have throw UserServiceException instead");

//        Assert
    }

    @DisplayName("EmailVerificationException is handled")
    @Test
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException(){

//        Arrange
        when(usersRepository.save(any(User.class))).thenReturn(true);

        doThrow(EmailVerificationServiceException.class)
                .when(emailVerificationService)
                .scheduleEmailConfirmation(any(User.class));

//        doNothing().when(emailVerificationService).scheduleEmailConfirmation(any(User.class));

//        Act & Assert;
        assertThrows(UserServiceException.class,()->{
            userService.createUser(firstName,lastName,email,password,repeatPassword);
        }, "Should have throw UserServiceException instead");

//        Assert
        verify(emailVerificationService)
                .scheduleEmailConfirmation(any(User.class));

    }

    @DisplayName("Schedule Email Confirmation is executed")
    @Test
    void testCreatUser_whenUserCreated_schedulesEmailConfirmation(){
//        Arrange
        when(usersRepository.save(any(User.class))).thenReturn(true);

        doCallRealMethod().when(emailVerificationService)
                .scheduleEmailConfirmation(any(User.class));

//        Act
        userService.createUser(firstName,lastName,email,password,repeatPassword);

//        Assert
        verify(emailVerificationService)
                .scheduleEmailConfirmation(any(User.class));

    }


}


