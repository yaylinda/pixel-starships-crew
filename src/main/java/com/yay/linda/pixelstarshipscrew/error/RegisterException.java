package com.yay.linda.pixelstarshipscrew.error;

public class RegisterException extends RuntimeException {
    public RegisterException(String message) {
        super(message);
    }

    public static RegisterException usernameTaken(String username) {
        return new RegisterException(String.format("Username='%s' is unavailable.", username));
    }

    public static RegisterException emailTaken(String email) {
        return new RegisterException(String.format("Email='%s' is already associated with another account.", email));
    }
}
