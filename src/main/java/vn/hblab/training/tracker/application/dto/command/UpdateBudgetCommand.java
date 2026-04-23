package vn.hblab.training.tracker.application.dto.command;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateBudgetCommand(
        UUID id,
        String userId,
        BigDecimal limitAmount) {
}
