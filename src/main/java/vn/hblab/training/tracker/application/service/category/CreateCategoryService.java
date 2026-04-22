package vn.hblab.training.tracker.application.service.category;


import org.springframework.stereotype.Service;

import vn.hblab.training.tracker.application.dto.command.CreateCategoryCommand;
import vn.hblab.training.tracker.application.dto.response.CategoryResponse;
import vn.hblab.training.tracker.application.usecase.category.CreateCategoryUseCase;
import vn.hblab.training.tracker.domain.model.category.Category;
import vn.hblab.training.tracker.domain.repository.CategoryRepository;

@Service
public class CreateCategoryService implements CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse execute(CreateCategoryCommand command) {

        Category category = new Category(command.name(), command.userId());

        categoryRepository.save(category);

        return new CategoryResponse(category.getId(), category.getName());
    }

}
