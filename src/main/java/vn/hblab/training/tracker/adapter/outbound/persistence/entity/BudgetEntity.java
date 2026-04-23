package vn.hblab.training.tracker.adapter.outbound.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "budgets")
public class BudgetEntity {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(name = "limit_amount", nullable = false)
    private BigDecimal limitAmount;

    /**
     * Lưu tháng dạng String "yyyy-MM" (ví dụ: "2025-04").
     * Dùng tên cột "budget_month" để tránh xung đột với reserved keyword "MONTH" của H2.
     */
    @Column(name = "budget_month", nullable = false, length = 7)
    private String month;
}
