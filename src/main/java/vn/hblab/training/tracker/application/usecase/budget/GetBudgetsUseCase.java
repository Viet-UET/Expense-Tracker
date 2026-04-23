package vn.hblab.training.tracker.application.usecase.budget;

import vn.hblab.training.tracker.application.dto.response.BudgetResponse;

import java.time.YearMonth;
import java.util.List;

public interface GetBudgetsUseCase {
    List<BudgetResponse> execute(String userId, YearMonth month);
}
