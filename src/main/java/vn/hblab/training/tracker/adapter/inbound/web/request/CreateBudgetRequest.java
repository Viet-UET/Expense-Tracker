package vn.hblab.training.tracker.adapter.inbound.web.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public record CreateBudgetRequest(
        @NotNull(message = "categoryId không được null") UUID categoryId,
        @NotNull(message = "Hạn mức không được null")
        @DecimalMin(value = "0.01", message = "Hạn mức phải lớn hơn 0") BigDecimal limitAmount,
        String month) {

    /**
     * Nếu month không truyền → dùng tháng hiện tại.
     * Format mong đợi: "yyyy-MM" (ví dụ: "2025-04").
     */
    public YearMonth parsedMonth() {
        if (month == null || month.isBlank()) return YearMonth.now();
        try {
            return YearMonth.parse(month);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Định dạng tháng không hợp lệ. Dùng định dạng yyyy-MM, ví dụ: 2025-04");
        }
    }
}
