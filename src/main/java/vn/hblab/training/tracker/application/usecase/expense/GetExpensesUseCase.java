package vn.hblab.training.tracker.application.usecase.expense;

import vn.hblab.training.tracker.application.dto.response.ExpenseResponse;

import java.util.List;

public interface GetExpensesUseCase {
    List<ExpenseResponse> execute(String userId);
}
