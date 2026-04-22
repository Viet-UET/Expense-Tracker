package vn.hblab.training.tracker.application.usecase.category;

import vn.hblab.training.tracker.application.dto.command.UpdateCategoryCommand;
import vn.hblab.training.tracker.application.dto.response.CategoryResponse;

public interface UpdateCategoryUseCase {
    CategoryResponse execute(UpdateCategoryCommand command);
}
