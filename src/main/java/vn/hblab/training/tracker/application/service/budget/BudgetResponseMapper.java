package vn.hblab.training.tracker.application.service.budget;

import vn.hblab.training.tracker.application.dto.response.BudgetResponse;
import vn.hblab.training.tracker.domain.model.budget.Budget;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Helper nội bộ của application layer: tính spentAmount + status rồi tạo BudgetResponse.
 * Không phải Spring bean – chỉ là utility class với static method.
 */
class BudgetResponseMapper {

    private BudgetResponseMapper() { }

    static BudgetResponse toResponse(Budget budget, ExpenseRepository expenseRepository) {
        LocalDate from = budget.getMonth().atDay(1);
        LocalDate to   = budget.getMonth().atEndOfMonth();

        BigDecimal spent = expenseRepository.sumByUserIdAndCategoryIdAndDateBetween(
                budget.getUserId(), budget.getCategoryId(), from, to);

        BigDecimal remaining = budget.getLimitAmount().subtract(spent);
        String status = computeStatus(spent, budget.getLimitAmount());

        return new BudgetResponse(
                budget.getId(),
                budget.getCategoryId(),
                budget.getLimitAmount(),
                spent,
                remaining,
                status,
                budget.getMonth().toString());   // "yyyy-MM"
    }

    /**
     * EXCEEDED : spent >= limit
     * WARNING  : spent >= limit * 80%
     * SAFE     : otherwise
     */
    private static String computeStatus(BigDecimal spent, BigDecimal limit) {
        if (spent.compareTo(limit) >= 0) return "EXCEEDED";
        if (spent.multiply(BigDecimal.TEN).compareTo(limit.multiply(new BigDecimal("8"))) >= 0) return "WARNING";
        return "SAFE";
    }
}
