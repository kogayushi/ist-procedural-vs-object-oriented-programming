package com.example.ist.object.oriented.domain.model.identity;

import lombok.Data;

@Data
public class Person {
    
    private String firstName;
    private String lastName;
    private Sex sex;
    private ContactInformation contactInformation;
    
    public Person(String firstName, String lastName, Sex sex, ContactInformation contactInformation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.contactInformation = contactInformation;
    }

    public String fullName() {
        return this.firstName + " " + this.lastName;
    }
}
