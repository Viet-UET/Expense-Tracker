package vn.hblab.training.tracker.adapter.outbound.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.UserEntity;
import vn.hblab.training.tracker.adapter.outbound.persistence.mapper.UserMapper;
import vn.hblab.training.tracker.adapter.outbound.persistence.springdata.UserSpringDataRepository;
import vn.hblab.training.tracker.domain.model.user.User;
import vn.hblab.training.tracker.domain.repository.UserRepository;

@Repository
public class JpaUserRepository implements UserRepository {
    private final UserSpringDataRepository userSpringDataRepository;
    private final UserMapper userMapper;

    public JpaUserRepository(UserSpringDataRepository userSpringDataRepository, UserMapper userMapper) {
        this.userSpringDataRepository = userSpringDataRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userSpringDataRepository.save(userEntity);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userSpringDataRepository.findByUserName(userName)
                .map(userMapper::toDomain);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userSpringDataRepository.existsByUserName(userName);
    }
}
