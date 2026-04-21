package vn.hblab.training.tracker.domain.model.refreshtoken;

import java.time.LocalDateTime;
import java.util.UUID;

public class RefreshToken {
    private final String id;
    private final String token;
    private final String userId;
    private final LocalDateTime expiresAt;

    public RefreshToken(String userId) {
        this.id = UUID.randomUUID().toString();
        this.token = UUID.randomUUID().toString();
        this.userId = userId;
        this.expiresAt = LocalDateTime.now().plusDays(7);
    }

    public RefreshToken(String id, String token, String userId, LocalDateTime expiresAt) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.expiresAt = expiresAt;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public String getId()           { return id; }
    public String getToken()        { return token; }
    public String getUserId()       { return userId; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
}
