package vn.hblab.training.tracker.application.usecase.user;

import vn.hblab.training.tracker.application.dto.command.LoginCommand;
import vn.hblab.training.tracker.application.dto.response.AuthResponse;

public interface LoginUseCase {
    AuthResponse execute(LoginCommand command);
}
