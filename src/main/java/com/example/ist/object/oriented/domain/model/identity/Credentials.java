package com.example.ist.object.oriented.domain.model.identity;

import lombok.Value;

@Value
public class Credentials {
    private final Username username;
    private final Password password;
}
