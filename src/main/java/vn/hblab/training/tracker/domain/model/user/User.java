package vn.hblab.training.tracker.domain.model.user;

import java.util.UUID;

import java.time.LocalDateTime;

public class User {
    private final UUID id;
    private final String userName;
    private final String passwordHash;

    public User(UUID id, String userName, String passwordHash) {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("Username không được trống");
        }
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
    }

    // Constructor load từ DB
    public User(UUID id, String userName, String passwordHash, LocalDateTime createdAt) {
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
