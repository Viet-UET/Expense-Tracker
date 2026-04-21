package vn.hblab.training.tracker.adapter.outbound.persistence.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.RefreshTokenEntity;
import vn.hblab.training.tracker.adapter.outbound.persistence.springdata.RefreshTokenSpringDataRepository;
import vn.hblab.training.tracker.domain.model.refreshtoken.RefreshToken;
import vn.hblab.training.tracker.domain.repository.RefreshTokenRepository;

import java.util.Optional;

@Repository
public class JpaRefreshTokenRepository implements RefreshTokenRepository {

    private final RefreshTokenSpringDataRepository springDataRepo;

    public JpaRefreshTokenRepository(RefreshTokenSpringDataRepository springDataRepo) {
        this.springDataRepo = springDataRepo;
    }

    @Override
    public void save(RefreshToken refreshToken) {
        RefreshTokenEntity entity = new RefreshTokenEntity();
        entity.setId(refreshToken.getId());
        entity.setToken(refreshToken.getToken());
        entity.setUserId(refreshToken.getUserId());
        entity.setExpiresAt(refreshToken.getExpiresAt());
        springDataRepo.save(entity);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return springDataRepo.findByToken(token)
                .map(e -> new RefreshToken(e.getId(), e.getToken(), e.getUserId(), e.getExpiresAt()));
    }

    @Override
    @Transactional
    public void deleteByUserId(String userId) {
        springDataRepo.deleteByUserId(userId);
    }
}
