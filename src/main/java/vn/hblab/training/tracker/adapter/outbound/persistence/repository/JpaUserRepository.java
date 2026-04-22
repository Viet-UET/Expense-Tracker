package vn.hblab.training.tracker.adapter.outbound.persistence.repository;

import org.springframework.stereotype.Repository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.UserEntity;
import vn.hblab.training.tracker.adapter.outbound.persistence.springdata.UserSpringDataRepository;
import vn.hblab.training.tracker.domain.model.user.User;
import vn.hblab.training.tracker.domain.repository.UserRepository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final UserSpringDataRepository springDataRepo;

    public JpaUserRepository(UserSpringDataRepository springDataRepo) {
        this.springDataRepo = springDataRepo;
    }

    @Override
    public void save(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUserName(user.getUserName());
        entity.setHashPassWord(user.getPasswordHash());
        springDataRepo.save(entity);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return springDataRepo.findByUserName(userName)
                .map(e -> new User(e.getId(), e.getUserName(), e.getHashPassWord()));
    }

    @Override
    public boolean existsByUserName(String userName) {
        return springDataRepo.existsByUserName(userName);
    }
}
