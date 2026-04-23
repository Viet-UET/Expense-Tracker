package vn.hblab.training.tracker.application.service.budget;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.CreateBudgetCommand;
import vn.hblab.training.tracker.application.dto.response.BudgetResponse;
import vn.hblab.training.tracker.application.usecase.budget.CreateBudgetUseCase;
import vn.hblab.training.tracker.domain.exception.ConflictException;
import vn.hblab.training.tracker.domain.model.budget.Budget;
import vn.hblab.training.tracker.domain.repository.BudgetRepository;
import vn.hblab.training.tracker.domain.repository.ExpenseRepository;

@Service
public class CreateBudgetService implements CreateBudgetUseCase {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public CreateBudgetService(BudgetRepository budgetRepository, ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public BudgetResponse execute(CreateBudgetCommand command) {
        // Không cho phép trùng: cùng user + category + tháng
        budgetRepository.findByUserIdAndCategoryIdAndMonth(
                command.userId(), command.categoryId(), command.month())
                .ifPresent(b -> {
                    throw new ConflictException(
                            "Ngân sách cho danh mục này trong tháng " + command.month() + " đã tồn tại");
                });

        Budget budget = new Budget(
                command.userId(),
                command.categoryId(),
                command.limitAmount(),
                command.month());

        budgetRepository.save(budget);
        return BudgetResponseMapper.toResponse(budget, expenseRepository);
    }
}
