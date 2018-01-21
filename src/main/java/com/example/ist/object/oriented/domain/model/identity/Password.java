package com.example.ist.object.oriented.domain.model.identity;

import lombok.Value;
import org.springframework.util.StringUtils;

@Value
public class Password implements AuthenticationFactor{
    private final String value;

    public Password(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("password is required");
        }
        this.value = value;
    }
}
