package vn.hblab.training.tracker.application.usecase.user;

import vn.hblab.training.tracker.application.dto.command.RegisterUserCommand;

public interface RegisterUserUseCase {
    void execute(RegisterUserCommand command);
}
