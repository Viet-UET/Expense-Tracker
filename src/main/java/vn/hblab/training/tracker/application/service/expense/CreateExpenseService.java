package vn.hblab.training.tracker.application.service.expense;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.CreateExpenseCommand;
import vn.hblab.training.tracker.application.dto.response.ExpenseResponse;
import vn.hblab.training.tracker.application.usecase.expense.CreateExpenseUseCase;
import vn.hblab.training.tracker.domain.exception.NotFoundException;
import vn.hblab.training.tracker.domain.model.expense.Expense;
import vn.hblab.training.tracker.domain.repository.CategoryRepository;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;

@Service
public class CreateExpenseService implements CreateExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public CreateExpenseService(ExpenseRepository expenseRepository,
                                CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ExpenseResponse execute(CreateExpenseCommand command) {
        // Validate categoryId tồn tại
        categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new NotFoundException(
                        "Danh mục không tồn tại: " + command.categoryId()));

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
