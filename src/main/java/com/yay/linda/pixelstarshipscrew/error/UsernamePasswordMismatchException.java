package com.yay.linda.pixelstarshipscrew.error;

public class UsernamePasswordMismatchException extends RuntimeException {
    public UsernamePasswordMismatchException(String username) {
        super(String.format("Incorrect password for username='%s'.", username));
    }
}
