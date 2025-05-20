package org.example.service;

import org.example.data.UsersRepository;
import org.example.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;
    EmailVerificationService emailVerificationService;

    public UserServiceImpl(UsersRepository usersRepository, EmailVerificationService emailVerificationService) {
        this.usersRepository = usersRepository;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public User createUser(String firstName, String lastName, String email,
                           String password, String repeatPassword) {

        if(firstName == null || firstName.trim().length() == 0){
            throw new IllegalArgumentException("User's first name is empty");
        }

        User user = new User(firstName,lastName,email, UUID.randomUUID().toString());

        boolean isUserCreated;


        try {
            isUserCreated = usersRepository.save(user);
        }catch (RuntimeException ex){
            throw new UserServiceException(ex.getMessage());
        }

        if(!isUserCreated) throw new UserServiceException("Could not create user");

        try{
            emailVerificationService.scheduleEmailConfirmation(user);
        }catch(RuntimeException ex){
            throw new UserServiceException(ex.getMessage());
        }

        return user;
    }

    public void demoMethod(){
        System.out.println("Demo added should have a red bar when run test with code coverage");
    }
}
