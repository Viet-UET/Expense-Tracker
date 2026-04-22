package vn.hblab.training.tracker.adapter.inbound.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import vn.hblab.training.tracker.adapter.inbound.web.request.CreateExpenseRequest;
import vn.hblab.training.tracker.adapter.inbound.web.request.UpdateExpenseRequest;
import vn.hblab.training.tracker.application.dto.command.CreateExpenseCommand;
import vn.hblab.training.tracker.application.dto.command.UpdateExpenseCommand;
import vn.hblab.training.tracker.application.dto.response.ExpenseResponse;
import vn.hblab.training.tracker.application.usecase.expense.CreateExpenseUseCase;
import vn.hblab.training.tracker.application.usecase.expense.DeleteExpenseUseCase;
import vn.hblab.training.tracker.application.usecase.expense.GetExpensesUseCase;
import vn.hblab.training.tracker.application.usecase.expense.UpdateExpenseUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final CreateExpenseUseCase createExpenseUseCase;
    private final GetExpensesUseCase getExpensesUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;

    public ExpenseController(CreateExpenseUseCase createExpenseUseCase,
            GetExpensesUseCase getExpensesUseCase,
            UpdateExpenseUseCase updateExpenseUseCase,
            DeleteExpenseUseCase deleteExpenseUseCase) {
        this.createExpenseUseCase = createExpenseUseCase;
        this.getExpensesUseCase = getExpensesUseCase;
        this.updateExpenseUseCase = updateExpenseUseCase;
        this.deleteExpenseUseCase = deleteExpenseUseCase;
    }

    private String currentUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> create(@Valid @RequestBody CreateExpenseRequest request) {
        ExpenseResponse response = createExpenseUseCase.execute(new CreateExpenseCommand(
                currentUserId(),
                request.categoryId(),
                request.amount(),
                request.description(),
                request.parsedDate()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAll() {
        return ResponseEntity.ok(getExpensesUseCase.execute(currentUserId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateExpenseRequest request) {
        return ResponseEntity.ok(updateExpenseUseCase.execute(new UpdateExpenseCommand(
                id,
                currentUserId(),
                request.categoryId(),
                request.amount(),
                request.description(),
                request.parsedDate())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteExpenseUseCase.execute(id, currentUserId());
        return ResponseEntity.noContent().build();
    }
}
