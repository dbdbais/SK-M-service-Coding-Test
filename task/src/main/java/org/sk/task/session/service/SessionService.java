package org.sk.task.session.service;

import org.sk.task.common.code.StatusCode;
import org.sk.task.session.entity.UserSession;
import org.sk.task.session.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public boolean isLogin(Long userId){
        return sessionRepository.existsByUserId(userId);
    }

    public Long getUserIdBySessionId(String sessionId){
        Optional<UserSession> userSession = sessionRepository.findBySessionId(sessionId);

        return userSession.map(UserSession::getUserId).orElse(null);
    }

    public boolean sessionCheck(Long userId, String sessionId){
        return sessionRepository.findById(userId).get().getSessionId().equals(sessionId);
    }


    public void createSession(Long userId, String sessionId){
        UserSession session = UserSession.builder()
                .sessionId(sessionId)
                .userId(userId)
                .expiryTime(LocalDateTime.now().plusMinutes(30))
                .build();

        sessionRepository.save(session);
    }

    public void deleteSession(Long userId) {
        sessionRepository.deleteById(userId);
    }

    @Scheduled(fixedRate = 60000)
    public void cleanExpiredSession(){
        LocalDateTime now = LocalDateTime.now();
        sessionRepository.deleteByExpiryTimeBefore(now);
    }

}
