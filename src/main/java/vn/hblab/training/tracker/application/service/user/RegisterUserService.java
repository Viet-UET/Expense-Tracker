package vn.hblab.training.tracker.application.service.user;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.RegisterUserCommand;
import vn.hblab.training.tracker.application.usecase.user.RegisterUserUseCase;
import vn.hblab.training.tracker.domain.model.user.User;
import vn.hblab.training.tracker.domain.repository.UserRepository;

import java.util.UUID;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(RegisterUserCommand command) {
        if (userRepository.existsByUserName(command.userName())) {
            throw new RuntimeException("Username đã tồn tại");
        }

        String hashPassword = BCrypt.hashpw(command.passWord(), BCrypt.gensalt());

        User newUser = new User(UUID.randomUUID(), command.userName(), hashPassword);

        userRepository.save(newUser);
    }
}
