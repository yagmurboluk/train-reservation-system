package com.project.trainreservation.entity;

import com.project.trainreservation.enums.Gender;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Column(nullable = false) // Soyadı zorunlu alan
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(nullable = true) // Şifre alanını opsiyonel yapar
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // Getters and Setters

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
