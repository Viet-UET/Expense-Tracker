package vn.hblab.training.tracker.adapter.outbound.persistence.repository;

import org.springframework.stereotype.Repository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.CategoryEntity;
import vn.hblab.training.tracker.adapter.outbound.persistence.springdata.CategorySpringDataRepository;
import vn.hblab.training.tracker.domain.model.category.Category;
import vn.hblab.training.tracker.domain.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final CategorySpringDataRepository springDataRepo;

    public JpaCategoryRepository(CategorySpringDataRepository springDataRepo) {
        this.springDataRepo = springDataRepo;
    }

    @Override
    public void save(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setUserId(category.getUserId());
        springDataRepo.save(entity);
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return springDataRepo.findById(id)
                .map(e -> new Category(e.getId(), e.getName(), e.getUserId()));
    }

    @Override
    public List<Category> findByUserId(String userId) {
        return springDataRepo.findByUserId(userId)
                .stream()
                .map(e -> new Category(e.getId(), e.getName(), e.getUserId()))
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        springDataRepo.deleteById(id);
    }
}
