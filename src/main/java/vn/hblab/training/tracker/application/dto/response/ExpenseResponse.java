package vn.hblab.training.tracker.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ExpenseResponse(
        UUID id,
        UUID categoryId,
        BigDecimal amount,
        String description,
        LocalDate date) {
}
