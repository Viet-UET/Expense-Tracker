package vn.hblab.training.tracker.application.service.user;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.LoginCommand;
import vn.hblab.training.tracker.application.dto.response.AuthResponse;
import vn.hblab.training.tracker.application.security.TokenGenerator;
import vn.hblab.training.tracker.application.usecase.user.LoginUseCase;
import vn.hblab.training.tracker.domain.model.refreshtoken.RefreshToken;
import vn.hblab.training.tracker.domain.model.user.User;
import vn.hblab.training.tracker.domain.repository.RefreshTokenRepository;
import vn.hblab.training.tracker.domain.repository.UserRepository;
import vn.hblab.training.tracker.domain.exception.NotFoundException;
import vn.hblab.training.tracker.domain.exception.UnauthorizedException;

@Service
public class LoginService implements LoginUseCase {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenGenerator tokenGenerator;

    public LoginService(UserRepository userRepository,
                        RefreshTokenRepository refreshTokenRepository,
                        TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public AuthResponse execute(LoginCommand loginCommand) {
        User user = userRepository.findByUserName(loginCommand.userName())
                .orElseThrow(() -> new NotFoundException("Username không tồn tại"));

        if (!BCrypt.checkpw(loginCommand.passWord(), user.getPasswordHash())) {
            throw new UnauthorizedException("Sai mật khẩu");
        }

        String accessToken = tokenGenerator.generate(user.getUserName());

        RefreshToken refreshToken = new RefreshToken(user.getUserName());
        refreshTokenRepository.save(refreshToken);

        return new AuthResponse(accessToken, refreshToken.getToken());
    }
}
