package vn.hblab.training.tracker.application.usecase.category;

import java.util.UUID;

public interface DeleteCategoryUseCase {
    void execute(UUID id, String userId);
}
