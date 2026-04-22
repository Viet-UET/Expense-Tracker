package vn.hblab.training.tracker.application.usecase.expense;

import java.util.UUID;

public interface DeleteExpenseUseCase {
    void execute(UUID id, String userId);
}
