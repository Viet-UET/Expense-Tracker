package vn.hblab.training.tracker.application.service.user;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.RefreshTokenCommand;
import vn.hblab.training.tracker.application.dto.response.AuthResponse;
import vn.hblab.training.tracker.application.security.TokenGenerator;
import vn.hblab.training.tracker.application.usecase.user.RefreshTokenUseCase;
import vn.hblab.training.tracker.domain.model.refreshtoken.RefreshToken;
import vn.hblab.training.tracker.domain.repository.RefreshTokenRepository;
import vn.hblab.training.tracker.domain.exception.NotFoundException;
import vn.hblab.training.tracker.domain.exception.UnauthorizedException;

@Service
public class RefreshTokenService implements RefreshTokenUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenGenerator tokenGenerator;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               TokenGenerator tokenGenerator) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public AuthResponse execute(RefreshTokenCommand command) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(command.refreshToken())
                .orElseThrow(() -> new RuntimeException("Refresh token không hợp lệ"));

        if (refreshToken.isExpired()) {
            throw new UnauthorizedException("Refresh token đã hết hạn");
        }

        String newAccessToken = tokenGenerator.generate(refreshToken.getUserId());

        return new AuthResponse(newAccessToken, refreshToken.getToken());
    }
}

