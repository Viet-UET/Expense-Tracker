package vn.hblab.training.tracker.domain.model.budget;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

public class Budget {

    private final UUID id;
    private final String userId;
    private final UUID categoryId;
    private BigDecimal limitAmount;
    private final YearMonth month;

    /** Constructor tạo mới – sinh UUID tự động */
    public Budget(String userId, UUID categoryId, BigDecimal limitAmount, YearMonth month) {
        if (userId == null || userId.isBlank()) throw new IllegalArgumentException("userId không được trống");
        if (categoryId == null) throw new IllegalArgumentException("categoryId không được null");
        if (limitAmount == null || limitAmount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Hạn mức phải lớn hơn 0");
        if (month == null) throw new IllegalArgumentException("month không được null");

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.categoryId = categoryId;
        this.limitAmount = limitAmount;
        this.month = month;
    }

    /** Constructor reconstruct từ DB */
    public Budget(UUID id, String userId, UUID categoryId, BigDecimal limitAmount, YearMonth month) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.limitAmount = limitAmount;
        this.month = month;
    }

    public void updateLimit(BigDecimal newLimit) {
        if (newLimit == null || newLimit.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Hạn mức mới phải lớn hơn 0");
        this.limitAmount = newLimit;
    }

    public UUID getId()             { return id; }
    public String getUserId()       { return userId; }
    public UUID getCategoryId()     { return categoryId; }
    public BigDecimal getLimitAmount() { return limitAmount; }
    public YearMonth getMonth()     { return month; }
}
