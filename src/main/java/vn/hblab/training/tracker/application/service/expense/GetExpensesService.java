package vn.hblab.training.tracker.application.service.expense;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.response.ExpenseResponse;
import vn.hblab.training.tracker.application.usecase.expense.GetExpensesUseCase;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;

import java.util.List;

@Service
public class GetExpensesService implements GetExpensesUseCase {

    private final ExpenseRepository expenseRepository;

    public GetExpensesService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<ExpenseResponse> execute(String userId) {
        return expenseRepository.findByUserId(userId)
                .stream()
                .map(CreateExpenseService::toResponse)
                .toList();
    }
}
