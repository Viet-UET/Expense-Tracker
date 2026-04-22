package vn.hblab.training.tracker.application.usecase.category;

import vn.hblab.training.tracker.application.dto.response.CategoryResponse;

import java.util.List;

public interface GetCategoriesUseCase {
    List<CategoryResponse> execute(String userId);
}
