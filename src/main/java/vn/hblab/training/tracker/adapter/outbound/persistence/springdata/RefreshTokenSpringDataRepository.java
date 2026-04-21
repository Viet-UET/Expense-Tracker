package vn.hblab.training.tracker.adapter.outbound.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.RefreshTokenEntity;

import java.util.Optional;

public interface RefreshTokenSpringDataRepository extends JpaRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByUserId(String userId);
}
