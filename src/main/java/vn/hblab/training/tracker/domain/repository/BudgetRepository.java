package vn.hblab.training.tracker.domain.repository;

import vn.hblab.training.tracker.domain.model.budget.Budget;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository {
    void save(Budget budget);
    Optional<Budget> findById(UUID id);
    List<Budget> findByUserIdAndMonth(String userId, YearMonth month);
    Optional<Budget> findByUserIdAndCategoryIdAndMonth(String userId, UUID categoryId, YearMonth month);
    void deleteById(UUID id);
}
