package vn.hblab.training.tracker.application.dto.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseCommand(
        UUID id,
        String userId,
        UUID categoryId,
        BigDecimal amount,
        String description,
        LocalDate date) {
}
