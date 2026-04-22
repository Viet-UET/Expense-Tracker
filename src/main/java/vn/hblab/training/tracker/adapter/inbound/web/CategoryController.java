package vn.hblab.training.tracker.adapter.inbound.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import vn.hblab.training.tracker.adapter.inbound.web.request.CreateCategoryRequest;
import vn.hblab.training.tracker.adapter.inbound.web.request.UpdateCategoryRequest;
import vn.hblab.training.tracker.application.dto.command.CreateCategoryCommand;
import vn.hblab.training.tracker.application.dto.command.UpdateCategoryCommand;
import vn.hblab.training.tracker.application.dto.response.CategoryResponse;
import vn.hblab.training.tracker.application.usecase.category.CreateCategoryUseCase;
import vn.hblab.training.tracker.application.usecase.category.DeleteCategoryUseCase;
import vn.hblab.training.tracker.application.usecase.category.GetCategoriesUseCase;
import vn.hblab.training.tracker.application.usecase.category.UpdateCategoryUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoriesUseCase getCategoriesUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase,
            GetCategoriesUseCase getCategoriesUseCase,
            UpdateCategoryUseCase updateCategoryUseCase,
            DeleteCategoryUseCase deleteCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.getCategoriesUseCase = getCategoriesUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    private String currentUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CreateCategoryRequest request) {
        CategoryResponse response = createCategoryUseCase.execute(
                new CreateCategoryCommand(request.name(), currentUserId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(getCategoriesUseCase.execute(currentUserId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok(updateCategoryUseCase.execute(
                new UpdateCategoryCommand(id, request.name(), currentUserId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteCategoryUseCase.execute(id, currentUserId());
        return ResponseEntity.noContent().build();
    }
}
