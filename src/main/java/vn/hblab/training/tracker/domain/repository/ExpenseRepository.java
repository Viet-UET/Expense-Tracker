package vn.hblab.training.tracker.domain.repository;

import vn.hblab.training.tracker.domain.model.expense.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository {
    void save(Expense expense);
    Optional<Expense> findById(UUID id);
    List<Expense> findByUserId(String userId);
    void deleteById(UUID id);

    /**
     * Tính tổng chi tiêu của một user theo category trong khoảng ngày [from, to].
     * Dùng để tính spentAmount khi hiển thị budget.
     * Trả về 0 nếu không có expense nào phù hợp.
     */
    BigDecimal sumByUserIdAndCategoryIdAndDateBetween(String userId, UUID categoryId, LocalDate from, LocalDate to);
}
