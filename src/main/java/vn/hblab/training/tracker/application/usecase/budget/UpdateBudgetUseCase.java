package vn.hblab.training.tracker.application.usecase.budget;

import vn.hblab.training.tracker.application.dto.command.UpdateBudgetCommand;
import vn.hblab.training.tracker.application.dto.response.BudgetResponse;

public interface UpdateBudgetUseCase {
    BudgetResponse execute(UpdateBudgetCommand command);
}
