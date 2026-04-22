package vn.hblab.training.tracker.adapter.inbound.web.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseRequest(
        @NotNull(message = "categoryId không được null") UUID categoryId,
        @NotNull(message = "Số tiền không được null")
        @DecimalMin(value = "0.01", message = "Số tiền phải lớn hơn 0") BigDecimal amount,
        String description,
        String date) {

    public LocalDate parsedDate() {
        if (date == null || date.isBlank()) return LocalDate.now();
        return LocalDate.parse(date);
    }
}
