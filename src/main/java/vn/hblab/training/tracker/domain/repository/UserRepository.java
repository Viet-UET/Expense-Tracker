package vn.hblab.training.tracker.domain.repository;

import vn.hblab.training.tracker.domain.model.user.User;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
