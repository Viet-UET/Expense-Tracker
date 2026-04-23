package vn.hblab.training.tracker.application.service.expense;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.UpdateExpenseCommand;
import vn.hblab.training.tracker.application.dto.response.ExpenseResponse;
import vn.hblab.training.tracker.application.usecase.expense.UpdateExpenseUseCase;
import vn.hblab.training.tracker.domain.model.expense.Expense;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;
import vn.hblab.training.tracker.domain.exception.NotFoundException;
import vn.hblab.training.tracker.domain.exception.UnauthorizedException;

@Service
public class UpdateExpenseService implements UpdateExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public UpdateExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResponse execute(UpdateExpenseCommand command) {
        Expense existing = expenseRepository.findById(command.id())
                .orElseThrow(() -> new NotFoundException("Chi tiêu không tồn tại"));

        if (!existing.getUserId().equals(command.userId())) {
            throw new UnauthorizedException("Không có quyền chỉnh sửa chi tiêu này");
        }

        Expense updated = new Expense(
                command.id(),
                command.userId(),
                command.categoryId(),
                command.amount(),
                command.description(),
                command.date());
        expenseRepository.save(updated);
        return CreateExpenseService.toResponse(updated);
    }
}


