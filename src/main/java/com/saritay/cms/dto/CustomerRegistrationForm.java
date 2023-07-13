package com.saritay.cms.dto;

public record CustomerRegistrationForm (
        String firstName,
        String lastName,
        String email,
        String telNo,
        Gender gender,
        String password
){

}
