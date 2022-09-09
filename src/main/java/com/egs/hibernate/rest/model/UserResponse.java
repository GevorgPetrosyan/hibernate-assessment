package com.egs.hibernate.rest.model;

import java.time.LocalDate;

/**
 * @author Arman Karapetyan
 * @date 2022-09-08
 **/
public class UserResponse {
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;

    public UserResponse() {
    }

    public UserResponse(String username, String firstName, String lastName, LocalDate birthdate) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
