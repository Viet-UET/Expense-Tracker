package vn.hblab.training.tracker.application.dto.response;

public record ErrorResponse(int status, String error, String message) {
}
