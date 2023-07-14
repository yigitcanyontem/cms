package com.saritay.cms.dto;

import org.springframework.core.io.Resource;

public class CustomerDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String telNo;
    private Gender gender;
    private Resource profilePic;

    public CustomerDTO(Integer id, String firstName, String lastName, String email, String telNo, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telNo = telNo;
        this.gender = gender;
    }
    public CustomerDTO(Integer id, String firstName, String lastName, String email, String telNo, Gender gender,Resource profilePic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telNo = telNo;
        this.gender = gender;
        this.profilePic = profilePic;
    }
    public CustomerDTO(String firstName, String lastName, String email, String telNo, Gender gender,Resource profilePic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telNo = telNo;
        this.gender = gender;
        this.profilePic = profilePic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
