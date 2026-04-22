package vn.hblab.training.tracker.domain.repository;

import vn.hblab.training.tracker.domain.model.expense.Expense;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository {
    void save(Expense expense);
    Optional<Expense> findById(UUID id);
    List<Expense> findByUserId(String userId);
    void deleteById(UUID id);
}
