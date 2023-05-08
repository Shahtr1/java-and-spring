package com.appsdeveloperblog.tutorials.junit.ui.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {

    @Size(min=2, message="First name must not be less than 2 characters")
    private String firstName;

    @Size(min=2, message="Last name must not be less than 2 characters")
    private String lastName;

    @Email
    private String email;

    @Size(min=8, max=16, message="Password must be equal to or greater than 8 characters and less than 16 characters")
    private String password;

    @Size(min=8, max=16, message="Repeat Password must be equal to or greater than 8 characters and less than 16 characters")
    private String repeatPassword;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
