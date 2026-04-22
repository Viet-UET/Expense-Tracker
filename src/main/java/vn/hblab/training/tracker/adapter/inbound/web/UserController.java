package vn.hblab.training.tracker.adapter.inbound.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import vn.hblab.training.tracker.adapter.inbound.web.request.LoginRequest;
import vn.hblab.training.tracker.adapter.inbound.web.request.RefreshTokenRequest;
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
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterUserRequest request) {
        registerUserUseCase.execute(new RegisterUserCommand(request.userName(), request.passWord()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = loginUseCase.execute(new LoginCommand(request.userName(), request.passWord()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(refreshTokenUseCase.execute(new RefreshTokenCommand(request.refreshToken())));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        logoutUseCase.execute(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok("Xin chào, " + userName + "!");
    }
}
