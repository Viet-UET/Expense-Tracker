package vn.hblab.training.tracker.application.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record BudgetResponse(
        UUID id,
        UUID categoryId,
        BigDecimal limitAmount,
        BigDecimal spentAmount,
        BigDecimal remainingAmount,
        String status,
        String month) {   // "yyyy-MM" — String để Jackson serialize không cần module đặc biệt
}
