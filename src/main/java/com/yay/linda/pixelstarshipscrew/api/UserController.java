package com.yay.linda.pixelstarshipscrew.api;

import com.yay.linda.pixelstarshipscrew.model.LoginRequest;
import com.yay.linda.pixelstarshipscrew.model.RegisterRequest;
import com.yay.linda.pixelstarshipscrew.model.User;
import com.yay.linda.pixelstarshipscrew.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{sessionToken}")
    public ResponseEntity<User> getUserFromSessionToken(@PathVariable("sessionToken") String sessionToken) {
        LOGGER.info("GET USER from sessionToken request: sessionToken={}", sessionToken);
        return ResponseEntity.ok(userService.getUserFromSessionToken(sessionToken));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        LOGGER.info("REGISTER request: {}", registerRequest);
        return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        LOGGER.info("LOGIN request: {}", loginRequest);
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PostMapping("/logout/{sessionToken}")
    public ResponseEntity<?> logout(@PathVariable("sessionToken") String sessionToken) {
        LOGGER.info("LOGOUT request: sessionToken={}", sessionToken);
        userService.logout(sessionToken);
        return ResponseEntity.noContent().build();
    }
}
