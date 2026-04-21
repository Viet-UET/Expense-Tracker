package vn.hblab.training.tracker.adapter.outbound.persistence.springdata;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.UserEntity;

public interface UserSpringDataRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUserName(String userName);

    boolean existsByUserName(String userName);
}
