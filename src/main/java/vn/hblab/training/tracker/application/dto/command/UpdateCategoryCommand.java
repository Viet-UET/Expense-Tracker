package vn.hblab.training.tracker.application.dto.command;

import java.util.UUID;

public record UpdateCategoryCommand(UUID id, String name, String userId) {
}
