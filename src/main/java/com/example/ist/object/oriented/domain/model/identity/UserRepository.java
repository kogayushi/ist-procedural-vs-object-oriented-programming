package com.example.ist.object.oriented.domain.model.identity;

import java.util.UUID;

public interface UserRepository {
    
    User userFromId(UUID id);

    void updatePassword(UUID id, Password password);

    void updateUsername(UUID id, Username username);
}
