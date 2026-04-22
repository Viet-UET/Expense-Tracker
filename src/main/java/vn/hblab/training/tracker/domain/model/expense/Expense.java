package vn.hblab.training.tracker.domain.model.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Expense {
    private final UUID id;
    private final String userId;
    private final UUID categoryId;
    private final BigDecimal amount;
    private final String description;
    private final LocalDate date;

    public Expense(String userId, UUID categoryId, BigDecimal amount, String description, LocalDate date) {
        if (userId == null || userId.isBlank()) throw new IllegalArgumentException("userId không được trống");
        if (categoryId == null) throw new IllegalArgumentException("categoryId không được null");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Số tiền phải lớn hơn 0");
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.description = description;
        this.date = date != null ? date : LocalDate.now();
    }

    public Expense(UUID id, String userId, UUID categoryId, BigDecimal amount, String description, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public UUID getId()          { return id; }
    public String getUserId()    { return userId; }
    public UUID getCategoryId()  { return categoryId; }
    public BigDecimal getAmount(){ return amount; }
    public String getDescription(){ return description; }
    public LocalDate getDate()   { return date; }
}
