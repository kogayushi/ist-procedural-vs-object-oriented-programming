package com.example.ist.object.oriented.domain.model.identity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private Credentials credentials;
    private Person person;

    public void changeUsernameWith(Username username) {
        this.credentials = new Credentials(username, this.credentials.getPassword());
    }

    public void changePasswordWith(Password password) {
        this.credentials = new Credentials(this.credentials.getUsername(), password);
    }
}