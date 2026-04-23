package vn.hblab.training.tracker.application.usecase.budget;

import vn.hblab.training.tracker.application.dto.command.CreateBudgetCommand;
import vn.hblab.training.tracker.application.dto.response.BudgetResponse;

public interface CreateBudgetUseCase {
    BudgetResponse execute(CreateBudgetCommand command);
}
