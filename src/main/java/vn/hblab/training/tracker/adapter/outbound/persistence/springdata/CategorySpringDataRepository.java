package vn.hblab.training.tracker.adapter.outbound.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.CategoryEntity;

import java.util.List;
import java.util.UUID;

public interface CategorySpringDataRepository extends JpaRepository<CategoryEntity, UUID> {
    List<CategoryEntity> findByUserId(String userId);
}
