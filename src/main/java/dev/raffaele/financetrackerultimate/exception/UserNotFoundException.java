package dev.raffaele.financetrackerultimate.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User not found.");
    }
}
