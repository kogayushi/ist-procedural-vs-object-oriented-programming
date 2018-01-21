package com.example.ist.object.oriented.domain.model.identity;

import lombok.Value;
import org.springframework.util.StringUtils;

@Value
public class Username implements AuthenticationFactor{
    private final String value;

    public Username(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("username is required");
        }
        this.value = value;
    }
}
