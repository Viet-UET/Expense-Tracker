package vn.hblab.training.tracker.application.usecase.budget;

import java.util.UUID;

public interface DeleteBudgetUseCase {
    void execute(UUID id, String userId);
}
