package vn.hblab.training.tracker.adapter.outbound.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.ExpenseEntity;

import java.util.List;
import java.util.UUID;

public interface ExpenseSpringDataRepository extends JpaRepository<ExpenseEntity, UUID> {
    List<ExpenseEntity> findByUserId(String userId);
}
