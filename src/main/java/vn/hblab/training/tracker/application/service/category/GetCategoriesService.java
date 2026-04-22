package vn.hblab.training.tracker.application.service.category;

import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.response.CategoryResponse;
import vn.hblab.training.tracker.application.usecase.category.GetCategoriesUseCase;
import vn.hblab.training.tracker.domain.repository.CategoryRepository;

import java.util.List;

@Service
public class GetCategoriesService implements GetCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public GetCategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponse> execute(String userId) {
        return categoryRepository.findByUserId(userId)
                .stream()
                .map(c -> new CategoryResponse(c.getId(), c.getName()))
                .toList();
    }
}
