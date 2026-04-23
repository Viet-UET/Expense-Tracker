package vn.hblab.training.tracker.application.service.category;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.UpdateCategoryCommand;
import vn.hblab.training.tracker.application.dto.response.CategoryResponse;
import vn.hblab.training.tracker.application.usecase.category.UpdateCategoryUseCase;
import vn.hblab.training.tracker.domain.model.category.Category;
import vn.hblab.training.tracker.domain.repository.CategoryRepository;
import vn.hblab.training.tracker.domain.exception.NotFoundException;
import vn.hblab.training.tracker.domain.exception.UnauthorizedException;

@Service
public class UpdateCategoryService implements UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public UpdateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse execute(UpdateCategoryCommand command) {
        Category existing = categoryRepository.findById(command.id())
                .orElseThrow(() -> new NotFoundException("Category không tồn tại"));

        if (!existing.getUserId().equals(command.userId())) {
            throw new UnauthorizedException("Không có quyền chỉnh sửa category này");
        }

        Category updated = new Category(command.id(), command.name(), command.userId());
        categoryRepository.save(updated);

        return new CategoryResponse(updated.getId(), updated.getName());
    }
}
