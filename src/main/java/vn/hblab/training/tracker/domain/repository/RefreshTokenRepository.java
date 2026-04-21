package vn.hblab.training.tracker.domain.repository;

import vn.hblab.training.tracker.domain.model.refreshtoken.RefreshToken;
import java.util.Optional;

public interface RefreshTokenRepository {
    void save(RefreshToken refreshToken);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserId(String userId);
}
