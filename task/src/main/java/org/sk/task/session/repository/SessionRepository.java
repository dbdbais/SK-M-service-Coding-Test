package org.sk.task.session.repository;

import org.sk.task.session.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<UserSession, Long> {
    boolean existsByUserId(Long userId);
    boolean existsBySessionId(String sessionId);
    Optional<UserSession> findBySessionId(String sessionId);
    void deleteByExpiryTimeBefore(LocalDateTime expiryTime);
}
