package vn.hblab.training.tracker.application.dto.command;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

public record CreateBudgetCommand(
        String userId,
        UUID categoryId,
        BigDecimal limitAmount,
        YearMonth month) {
}
