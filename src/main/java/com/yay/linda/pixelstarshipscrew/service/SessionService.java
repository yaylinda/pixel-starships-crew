package com.yay.linda.pixelstarshipscrew.service;

import com.yay.linda.pixelstarshipscrew.entity.SessionEntity;
import com.yay.linda.pixelstarshipscrew.error.NotFoundException;
import com.yay.linda.pixelstarshipscrew.error.SessionExpiredException;
import com.yay.linda.pixelstarshipscrew.repository.SessionRepository;
import com.yay.linda.pixelstarshipscrew.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public String getUsernameFromSessionToken(String sessionToken) {
        LOGGER.info("Looking up sessionToken={}", sessionToken);
        Optional<SessionEntity> optionalSession = sessionRepository.findBySessionToken(sessionToken);
        if (optionalSession.isPresent()) {

            SessionEntity session = optionalSession.get();
            LOGGER.info("Found session: {}", session);

            if (!session.getIsActive()) {
                throw new SessionExpiredException(sessionToken, session.getUsername());
            }

            return session.getUsername();
        } else {
            throw NotFoundException.sessionTokenNotFound(sessionToken);
        }
    }

    public SessionEntity createSession(String username) {
        SessionEntity session = new SessionEntity(username);
        LOGGER.info("Creating new Session: {}", session);
        sessionRepository.save(session);
        return session;
    }

    public void deleteSession(String sessionToken) {
        LOGGER.info("Deleting active session for sessionToken={}", sessionToken);
        sessionRepository.deleteBySessionToken(sessionToken);
    }

}
