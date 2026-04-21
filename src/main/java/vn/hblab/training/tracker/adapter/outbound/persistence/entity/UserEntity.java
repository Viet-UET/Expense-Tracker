package vn.hblab.training.tracker.adapter.outbound.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @Column
    private UUID id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "hash_pass_word")
    private String hashPassWord;

}
