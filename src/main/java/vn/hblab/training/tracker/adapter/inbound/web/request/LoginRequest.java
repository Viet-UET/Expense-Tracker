package vn.hblab.training.tracker.adapter.inbound.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Username không được trống") String userName,
        @NotBlank(message = "Password không được trống")
        @Size(min = 6, message = "Password phải có ít nhất 6 ký tự") String passWord) {
}

