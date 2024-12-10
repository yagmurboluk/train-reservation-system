package com.project.trainreservation.dto;

import com.project.trainreservation.enums.Gender;

public class UserDTO {
    private Long userId; // Eksik olan userId eklendi
    private String name;
    private String lastName; // Soyadı eklendi
    private String email;
    private String password; // Şifre eklenebilir
    private Gender gender;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() { // Soyadı getter
        return lastName;
    }

    public void setLastName(String lastName) { // Soyadı setter
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
