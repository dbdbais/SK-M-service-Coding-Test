package org.sk.task.user.repoisitory;

import org.sk.task.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByNickname(String nickname);
    boolean existsByNickname(String nickname);
}
