package vn.hblab.training.tracker.application.service.category;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.usecase.category.DeleteCategoryUseCase;
import vn.hblab.training.tracker.domain.model.category.Category;
import vn.hblab.training.tracker.domain.repository.CategoryRepository;

import java.util.UUID;
import vn.hblab.training.tracker.domain.exception.NotFoundException;
import vn.hblab.training.tracker.domain.exception.UnauthorizedException;

@Service
public class DeleteCategoryService implements DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(UUID id, String userId) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category không tồn tại"));

        if (!existing.getUserId().equals(userId)) {
            throw new UnauthorizedException("Không có quyền xóa category này");
        }

        categoryRepository.deleteById(id);
    }
}


