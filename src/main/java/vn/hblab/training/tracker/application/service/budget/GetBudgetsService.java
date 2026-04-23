package vn.hblab.training.tracker.application.service.budget;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.response.BudgetResponse;
import vn.hblab.training.tracker.application.usecase.budget.GetBudgetsUseCase;
import vn.hblab.training.tracker.domain.repository.BudgetRepository;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;

import java.time.YearMonth;
import java.util.List;

@Service
public class GetBudgetsService implements GetBudgetsUseCase {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public GetBudgetsService(BudgetRepository budgetRepository, ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<BudgetResponse> execute(String userId, YearMonth month) {
        return budgetRepository.findByUserIdAndMonth(userId, month)
                .stream()
                .map(b -> BudgetResponseMapper.toResponse(b, expenseRepository))
                .toList();
    }
}
