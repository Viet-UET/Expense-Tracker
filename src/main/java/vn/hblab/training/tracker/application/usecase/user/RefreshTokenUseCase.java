package vn.hblab.training.tracker.application.usecase.user;

import vn.hblab.training.tracker.application.dto.command.RefreshTokenCommand;
import vn.hblab.training.tracker.application.dto.response.AuthResponse;

public interface RefreshTokenUseCase {
    AuthResponse execute(RefreshTokenCommand command);
}
