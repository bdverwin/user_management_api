package com.benhur.user_management.dto;

import jakarta.validation.constraints.NotBlank;
public class UserRequest {

    @NotBlank(message = "First name is mandatory.")
    private String firstName;

    @NotBlank(message = "Last name is mandatory.")
    private String lastName;


    @NotBlank(message = "Email is mandatory.")
    private String email;

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

}
