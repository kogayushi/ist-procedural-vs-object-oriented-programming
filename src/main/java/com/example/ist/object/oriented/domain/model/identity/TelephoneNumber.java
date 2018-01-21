package com.example.ist.object.oriented.domain.model.identity;

import lombok.Value;

import java.util.regex.Pattern;

@Value
public class TelephoneNumber {
    private String value;

    public TelephoneNumber(String value) {
        if (!Pattern.matches("^0\\d{1,4}-\\d{1,4}-\\d{4}$", value)) {
            throw new IllegalArgumentException("It's not valid telephone number.");
        }
        this.value = value;
    }
}
