package vn.hblab.training.tracker.application.service.expense;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.CreateExpenseCommand;
import vn.hblab.training.tracker.application.dto.response.ExpenseResponse;
import vn.hblab.training.tracker.application.usecase.expense.CreateExpenseUseCase;
import vn.hblab.training.tracker.domain.model.expense.Expense;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;

@Service
public class CreateExpenseService implements CreateExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public CreateExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResponse execute(CreateExpenseCommand command) {
        Expense expense = new Expense(
                command.userId(),
                command.categoryId(),
                command.amount(),
                command.description(),
                command.date());
        expenseRepository.save(expense);
        return toResponse(expense);
    }

    static ExpenseResponse toResponse(Expense e) {
        return new ExpenseResponse(e.getId(), e.getCategoryId(), e.getAmount(), e.getDescription(), e.getDate());
    }
}
