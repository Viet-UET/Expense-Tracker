package vn.hblab.training.tracker.adapter.inbound.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import vn.hblab.training.tracker.adapter.inbound.web.request.CreateBudgetRequest;
import vn.hblab.training.tracker.adapter.inbound.web.request.UpdateBudgetRequest;
import vn.hblab.training.tracker.application.dto.command.CreateBudgetCommand;
import vn.hblab.training.tracker.application.dto.command.UpdateBudgetCommand;
import vn.hblab.training.tracker.application.dto.response.BudgetResponse;
import vn.hblab.training.tracker.application.usecase.budget.CreateBudgetUseCase;
import vn.hblab.training.tracker.application.usecase.budget.DeleteBudgetUseCase;
import vn.hblab.training.tracker.application.usecase.budget.GetBudgetsUseCase;
import vn.hblab.training.tracker.application.usecase.budget.UpdateBudgetUseCase;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final CreateBudgetUseCase createBudgetUseCase;
    private final GetBudgetsUseCase   getBudgetsUseCase;
    private final UpdateBudgetUseCase updateBudgetUseCase;
    private final DeleteBudgetUseCase deleteBudgetUseCase;

    public BudgetController(CreateBudgetUseCase createBudgetUseCase,
                            GetBudgetsUseCase getBudgetsUseCase,
                            UpdateBudgetUseCase updateBudgetUseCase,
                            DeleteBudgetUseCase deleteBudgetUseCase) {
        this.createBudgetUseCase = createBudgetUseCase;
        this.getBudgetsUseCase   = getBudgetsUseCase;
        this.updateBudgetUseCase = updateBudgetUseCase;
        this.deleteBudgetUseCase = deleteBudgetUseCase;
    }

    private String currentUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /** POST /api/budgets */
    @PostMapping
    public ResponseEntity<BudgetResponse> create(@Valid @RequestBody CreateBudgetRequest request) {
        BudgetResponse response = createBudgetUseCase.execute(new CreateBudgetCommand(
                currentUserId(),
                request.categoryId(),
                request.limitAmount(),
                request.parsedMonth()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/budgets?month=2025-04
     * Nếu không truyền month → tháng hiện tại.
     */
    @GetMapping
    public ResponseEntity<List<BudgetResponse>> getAll(
            @RequestParam(required = false) String month) {
        YearMonth targetMonth = parseMonthParam(month);
        return ResponseEntity.ok(getBudgetsUseCase.execute(currentUserId(), targetMonth));
    }

    /** PUT /api/budgets/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponse> update(@PathVariable UUID id,
                                                  @Valid @RequestBody UpdateBudgetRequest request) {
        return ResponseEntity.ok(updateBudgetUseCase.execute(
                new UpdateBudgetCommand(id, currentUserId(), request.limitAmount())));
    }

    /** DELETE /api/budgets/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteBudgetUseCase.execute(id, currentUserId());
        return ResponseEntity.noContent().build();
    }

    private YearMonth parseMonthParam(String month) {
        if (month == null || month.isBlank()) return YearMonth.now();
        try {
            return YearMonth.parse(month);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Định dạng tháng không hợp lệ. Dùng định dạng yyyy-MM, ví dụ: 2025-04");
        }
    }
}
