package vn.hblab.training.tracker.adapter.inbound.web.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank(message = "Tên danh mục không được trống") String name) {
}
