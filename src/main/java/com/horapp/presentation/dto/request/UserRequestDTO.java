package com.horapp.presentation.dto.request;

public class UserRequestDTO {
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String password;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String username, String name, String lastname, String email) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
}
