package vn.hblab.training.tracker.application.dto.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateExpenseCommand(
        String userId,
        UUID categoryId,
        BigDecimal amount,
        String description,
        LocalDate date) {
}
