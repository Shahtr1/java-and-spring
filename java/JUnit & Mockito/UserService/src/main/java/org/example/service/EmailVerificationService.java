package org.example.service;

import org.example.model.User;

public interface EmailVerificationService {
    void scheduleEmailConfirmation(User user);
}
