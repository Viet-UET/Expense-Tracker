package vn.hblab.training.tracker.adapter.inbound.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "Username không được trống")
        @Size(min = 3, message = "Username phải có ít nhất 3 ký tự") String userName,
        @NotBlank(message = "Password không được trống")
        @Size(min = 6, message = "Password phải có ít nhất 6 ký tự") String passWord) {
}
