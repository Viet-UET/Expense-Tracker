package vn.hblab.training.tracker.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import vn.hblab.training.tracker.domain.model.category.Category;

public interface CategoryRepository {
    void save(Category category);
    Optional<Category> findById(UUID id);
    List<Category> findByUserId(String userId);
    void deleteById(UUID id);
}
