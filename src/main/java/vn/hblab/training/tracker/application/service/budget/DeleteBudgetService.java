package vn.hblab.training.tracker.application.service.budget;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.usecase.budget.DeleteBudgetUseCase;
import vn.hblab.training.tracker.domain.exception.NotFoundException;
import vn.hblab.training.tracker.domain.exception.UnauthorizedException;
import vn.hblab.training.tracker.domain.model.budget.Budget;
import vn.hblab.training.tracker.domain.repository.BudgetRepository;

import java.util.UUID;

@Service
public class DeleteBudgetService implements DeleteBudgetUseCase {

    private final BudgetRepository budgetRepository;

    public DeleteBudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public void execute(UUID id, String userId) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy ngân sách với id: " + id));

        if (!budget.getUserId().equals(userId)) {
            throw new UnauthorizedException("Bạn không có quyền xoá ngân sách này");
        }

        budgetRepository.deleteById(id);
    }
}
