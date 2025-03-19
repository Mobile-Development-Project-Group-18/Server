package com.goshell.goshell.user;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class User {

    @NotEmpty
    private String id;

    private String firstName;
    private String lastName;

    @NotEmpty
    private Boolean emailVerify;

    private LocalDate createDate;
    private LocalDate dateOfBirth;

    private String avatar;
    private String address;

    // No-argument constructor
    public User() {}

    // Constructor with all fields
    public User(String id, String firstName, String lastName, Boolean emailVerify,
                LocalDate createDate, LocalDate dateOfBirth, String avatar, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailVerify = emailVerify;
        this.createDate = createDate;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
        this.address = address;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(Boolean emailVerify) {
        this.emailVerify = emailVerify;
    }

//        public LocalDate getCreateDate() {
//                return createDate;
//        }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

//        public LocalDate getDateOfBirth() {
//                return dateOfBirth;
//        }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
