package vn.hblab.training.tracker.adapter.outbound.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.BudgetEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BudgetSpringDataRepository extends JpaRepository<BudgetEntity, UUID> {

    List<BudgetEntity> findByUserIdAndMonth(String userId, String month);

    Optional<BudgetEntity> findByUserIdAndCategoryIdAndMonth(String userId, UUID categoryId, String month);
}
