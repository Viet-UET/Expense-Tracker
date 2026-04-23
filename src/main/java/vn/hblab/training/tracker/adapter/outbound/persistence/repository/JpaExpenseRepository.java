package vn.hblab.training.tracker.adapter.outbound.persistence.repository;

import org.springframework.stereotype.Repository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.ExpenseEntity;
import vn.hblab.training.tracker.adapter.outbound.persistence.springdata.ExpenseSpringDataRepository;
import vn.hblab.training.tracker.domain.model.expense.Expense;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaExpenseRepository implements ExpenseRepository {

    private final ExpenseSpringDataRepository springDataRepo;

    public JpaExpenseRepository(ExpenseSpringDataRepository springDataRepo) {
        this.springDataRepo = springDataRepo;
    }

    @Override
    public void save(Expense expense) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setId(expense.getId());
        entity.setUserId(expense.getUserId());
        entity.setCategoryId(expense.getCategoryId());
        entity.setAmount(expense.getAmount());
        entity.setDescription(expense.getDescription());
        entity.setDate(expense.getDate());
        springDataRepo.save(entity);
    }

    @Override
    public Optional<Expense> findById(UUID id) {
        return springDataRepo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Expense> findByUserId(String userId) {
        return springDataRepo.findByUserId(userId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepo.deleteById(id);
    }

    @Override
    public BigDecimal sumByUserIdAndCategoryIdAndDateBetween(
            String userId, UUID categoryId, LocalDate from, LocalDate to) {
        return springDataRepo.sumAmountByUserIdAndCategoryIdAndDateBetween(userId, categoryId, from, to);
    }

    private Expense toDomain(ExpenseEntity e) {
        return new Expense(e.getId(), e.getUserId(), e.getCategoryId(),
                e.getAmount(), e.getDescription(), e.getDate());
    }
}
