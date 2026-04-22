package vn.hblab.training.tracker.application.usecase.category;

import vn.hblab.training.tracker.application.dto.command.CreateCategoryCommand;
import vn.hblab.training.tracker.application.dto.response.CategoryResponse;

public interface CreateCategoryUseCase {
    CategoryResponse execute(CreateCategoryCommand command);
}
