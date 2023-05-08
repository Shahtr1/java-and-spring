package org.example.data;

import org.example.model.User;

public interface UsersRepository {
    boolean save(User user);
}
