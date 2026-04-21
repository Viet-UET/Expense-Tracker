package vn.hblab.training.tracker.application.service.user;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.usecase.user.LogoutUseCase;
import vn.hblab.training.tracker.domain.repository.RefreshTokenRepository;

@Service
public class LogoutService implements LogoutUseCase {

    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void execute(String userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}
