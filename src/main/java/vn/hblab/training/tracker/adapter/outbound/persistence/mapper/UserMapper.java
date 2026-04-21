package vn.hblab.training.tracker.adapter.outbound.persistence.mapper;

import org.springframework.stereotype.Component;

import vn.hblab.training.tracker.adapter.outbound.persistence.entity.UserEntity;
import vn.hblab.training.tracker.domain.model.user.User;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());           // UUID → UUID
        entity.setUserName(user.getUserName());
        entity.setHashPassWord(user.getPasswordHash());
        return entity;
    }

    public User toDomain(UserEntity entity) {
        return new User(
            entity.getId(),           // UUID
            entity.getUserName(),
            entity.getHashPassWord()
        );
    }
}

