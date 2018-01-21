package com.example.ist.procedural.entity;

import com.example.ist.procedural.enums.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Sex sex;
    private String telephoneNumber;
    private String mailAddress;
}