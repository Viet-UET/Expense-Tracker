package vn.hblab.training.tracker.adapter.inbound.web.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateBudgetRequest(
        @NotNull(message = "Hạn mức không được null")
        @DecimalMin(value = "0.01", message = "Hạn mức phải lớn hơn 0") BigDecimal limitAmount) {
}
