package com.example.ist.object.oriented.application.command;

import lombok.Value;

import java.util.UUID;

@Value
public class ChangeUsernameCommand {
    private final UUID id;
    private final String newUsername;
}
