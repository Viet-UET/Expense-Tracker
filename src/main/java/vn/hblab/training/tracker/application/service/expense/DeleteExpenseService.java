package vn.hblab.training.tracker.application.service.expense;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.usecase.expense.DeleteExpenseUseCase;
import vn.hblab.training.tracker.domain.model.expense.Expense;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;

import java.util.UUID;
import vn.hblab.training.tracker.domain.exception.NotFoundException;
import vn.hblab.training.tracker.domain.exception.UnauthorizedException;

@Service
public class DeleteExpenseService implements DeleteExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public DeleteExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void execute(UUID id, String userId) {
        Expense existing = expenseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Chi tiêu không tồn tại"));

        if (!existing.getUserId().equals(userId)) {
            throw new UnauthorizedException("Không có quyền xóa chi tiêu này");
        }

        expenseRepository.deleteById(id);
    }
}


