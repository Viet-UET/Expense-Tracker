package vn.hblab.training.tracker.application.service.budget;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.UpdateBudgetCommand;
import vn.hblab.training.tracker.application.dto.response.BudgetResponse;
import vn.hblab.training.tracker.application.usecase.budget.UpdateBudgetUseCase;
import vn.hblab.training.tracker.domain.exception.NotFoundException;
import vn.hblab.training.tracker.domain.exception.UnauthorizedException;
import vn.hblab.training.tracker.domain.model.budget.Budget;
import vn.hblab.training.tracker.domain.repository.BudgetRepository;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;

@Service
public class UpdateBudgetService implements UpdateBudgetUseCase {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public UpdateBudgetService(BudgetRepository budgetRepository, ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public BudgetResponse execute(UpdateBudgetCommand command) {
        Budget budget = budgetRepository.findById(command.id())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy ngân sách với id: " + command.id()));

        if (!budget.getUserId().equals(command.userId())) {
            throw new UnauthorizedException("Bạn không có quyền sửa ngân sách này");
        }

        budget.updateLimit(command.limitAmount());
        budgetRepository.save(budget);
        return BudgetResponseMapper.toResponse(budget, expenseRepository);
    }
}
