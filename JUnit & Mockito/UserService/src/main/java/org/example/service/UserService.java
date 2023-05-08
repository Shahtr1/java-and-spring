package org.example.service;

import org.example.model.User;

public interface UserService {

    User createUser(String firstName, String lastName, String email, String password, String repeatPassword);
}
