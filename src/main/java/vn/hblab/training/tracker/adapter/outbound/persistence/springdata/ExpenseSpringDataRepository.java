package vn.hblab.training.tracker.adapter.outbound.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.ExpenseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseSpringDataRepository extends JpaRepository<ExpenseEntity, UUID> {

    List<ExpenseEntity> findByUserId(String userId);

    @Query("""
            SELECT COALESCE(SUM(e.amount), 0)
            FROM ExpenseEntity e
            WHERE e.userId = :userId
              AND e.categoryId = :categoryId
              AND e.date BETWEEN :from AND :to
            """)
    BigDecimal sumAmountByUserIdAndCategoryIdAndDateBetween(
            @Param("userId") String userId,
            @Param("categoryId") UUID categoryId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to);
}
