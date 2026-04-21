package vn.hblab.training.tracker.adapter.inbound.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import vn.hblab.training.tracker.adapter.inbound.web.request.LoginRequest;
import vn.hblab.training.tracker.adapter.inbound.web.request.RegisterUserRequest;
import vn.hblab.training.tracker.application.dto.command.LoginCommand;
import vn.hblab.training.tracker.application.dto.command.RefreshTokenCommand;
import vn.hblab.training.tracker.application.dto.command.RegisterUserCommand;
import vn.hblab.training.tracker.application.dto.response.AuthResponse;
import vn.hblab.training.tracker.application.usecase.user.LoginUseCase;
import vn.hblab.training.tracker.application.usecase.user.LogoutUseCase;
import vn.hblab.training.tracker.application.usecase.user.RefreshTokenUseCase;
import vn.hblab.training.tracker.application.usecase.user.RegisterUserUseCase;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUseCase logoutUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase,
                          LoginUseCase loginUseCase,
                          RefreshTokenUseCase refreshTokenUseCase,
                          LogoutUseCase logoutUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUserRequest request) {
        try {
            RegisterUserCommand command = new RegisterUserCommand(request.userName(), request.passWord());
            registerUserUseCase.execute(command);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginCommand command = new LoginCommand(request.userName(), request.passWord());
            return ResponseEntity.ok(loginUseCase.execute(command));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenCommand command) {
        try {
            return ResponseEntity.ok(refreshTokenUseCase.execute(command));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        try {
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            logoutUseCase.execute(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok("Xin chào, " + userName + "!");
    }
}
