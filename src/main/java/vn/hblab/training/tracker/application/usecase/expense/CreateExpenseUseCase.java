package vn.hblab.training.tracker.application.usecase.expense;

import vn.hblab.training.tracker.application.dto.command.CreateExpenseCommand;
import vn.hblab.training.tracker.application.dto.response.ExpenseResponse;

public interface CreateExpenseUseCase {
    ExpenseResponse execute(CreateExpenseCommand command);
}
