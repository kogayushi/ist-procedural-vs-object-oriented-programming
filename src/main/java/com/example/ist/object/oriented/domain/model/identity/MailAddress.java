package com.example.ist.object.oriented.domain.model.identity;

import lombok.Value;

import java.util.regex.Pattern;

@Value
public class MailAddress {

    private String value;

    public MailAddress(String value) {
        if (!Pattern.matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", value)) {
            throw new IllegalArgumentException("it's not valid mail address.");
        }
        this.value = value;
    }
}
