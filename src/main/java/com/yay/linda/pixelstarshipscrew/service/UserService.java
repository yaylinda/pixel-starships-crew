package com.yay.linda.pixelstarshipscrew.service;

import com.yay.linda.pixelstarshipscrew.entity.SessionEntity;
import com.yay.linda.pixelstarshipscrew.error.RegisterException;
import com.yay.linda.pixelstarshipscrew.model.LoginRequest;
import com.yay.linda.pixelstarshipscrew.model.RegisterRequest;
import com.yay.linda.pixelstarshipscrew.model.User;
import com.yay.linda.pixelstarshipscrew.repository.UserRepository;
import com.yay.linda.pixelstarshipscrew.entity.UserEntity;
import com.yay.linda.pixelstarshipscrew.error.NotFoundException;
import com.yay.linda.pixelstarshipscrew.error.UsernamePasswordMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionService sessionService;

    public User getUserFromSessionToken(String sessionToken) {
        String username = sessionService.getUsernameFromSessionToken(sessionToken);

        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw NotFoundException.usernameNotFound(username);
        }

        return User.builder()
                .username(optionalUser.get().getUsername())
                .sessionToken(sessionToken)
                .build();
    }

    public User register(RegisterRequest registerRequest) {

        if (usernameExists(registerRequest.getUsername())) {
            throw RegisterException.usernameTaken(registerRequest.getUsername());
        }

        if (emailExists(registerRequest.getEmail())) {
            throw RegisterException.emailTaken(registerRequest.getEmail());
        }

        createUser(registerRequest);

        SessionEntity session = sessionService.createSession(registerRequest.getUsername());

        return User.builder()
                .username(registerRequest.getUsername())
                .sessionToken(session.getSessionToken())
                .build();
    }

    public User login(LoginRequest loginRequest) {

        if (!usernameExists(loginRequest.getUsername())) {
            throw NotFoundException.usernameNotFound(loginRequest.getUsername());
        }

        if (!verifyPassword(loginRequest.getUsername(), loginRequest.getPassword())) {
            throw new UsernamePasswordMismatchException(loginRequest.getUsername());
        }

        SessionEntity session = sessionService.createSession(loginRequest.getUsername());

        return User.builder()
                .username(loginRequest.getUsername())
                .sessionToken(session.getSessionToken())
                .build();
    }

    public void logout(String sessionToken) {
        sessionService.deleteSession(sessionToken);
    }

    /*-------------------------------------------------------------------------
        PRIVATE HELPER METHODS
     -------------------------------------------------------------------------*/

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean verifyPassword(String username, String password) {
        UserEntity user = userRepository.findByUsername(username).get();
        return (user.getPassword().equals(password));
    }

    private void createUser(RegisterRequest registerRequest) {
        LOGGER.info("Creating new UserEntity: {}", registerRequest);
        UserEntity user = new UserEntity(registerRequest);
        userRepository.save(user);
    }

    private void sendConfirmationEmail(RegisterRequest registerRequest) {
        // TODO
    }
}
