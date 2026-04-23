package vn.hblab.training.tracker.adapter.outbound.persistence.repository;

import org.springframework.stereotype.Repository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.BudgetEntity;
import vn.hblab.training.tracker.adapter.outbound.persistence.springdata.BudgetSpringDataRepository;
import vn.hblab.training.tracker.domain.model.budget.Budget;
import vn.hblab.training.tracker.domain.repository.BudgetRepository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaBudgetRepository implements BudgetRepository {

    private final BudgetSpringDataRepository springDataRepo;

    public JpaBudgetRepository(BudgetSpringDataRepository springDataRepo) {
        this.springDataRepo = springDataRepo;
    }

    @Override
    public void save(Budget budget) {
        BudgetEntity entity = new BudgetEntity();
        entity.setId(budget.getId());
        entity.setUserId(budget.getUserId());
        entity.setCategoryId(budget.getCategoryId());
        entity.setLimitAmount(budget.getLimitAmount());
        entity.setMonth(budget.getMonth().toString());   // "yyyy-MM"
        springDataRepo.save(entity);
    }

    @Override
    public Optional<Budget> findById(UUID id) {
        return springDataRepo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Budget> findByUserIdAndMonth(String userId, YearMonth month) {
        return springDataRepo.findByUserIdAndMonth(userId, month.toString())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Budget> findByUserIdAndCategoryIdAndMonth(String userId, UUID categoryId, YearMonth month) {
        return springDataRepo.findByUserIdAndCategoryIdAndMonth(userId, categoryId, month.toString())
                .map(this::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepo.deleteById(id);
    }

    private Budget toDomain(BudgetEntity e) {
        return new Budget(
                e.getId(),
                e.getUserId(),
                e.getCategoryId(),
                e.getLimitAmount(),
                YearMonth.parse(e.getMonth()));
    }
}
