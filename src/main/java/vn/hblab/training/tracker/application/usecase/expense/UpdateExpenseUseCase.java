package vn.hblab.training.tracker.application.usecase.expense;

import vn.hblab.training.tracker.application.dto.command.UpdateExpenseCommand;
import vn.hblab.training.tracker.application.dto.response.ExpenseResponse;

public interface UpdateExpenseUseCase {
    ExpenseResponse execute(UpdateExpenseCommand command);
}
