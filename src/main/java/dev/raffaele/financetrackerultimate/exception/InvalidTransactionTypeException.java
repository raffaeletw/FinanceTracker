package dev.raffaele.financetrackerultimate.exception;

public class InvalidTransactionTypeException extends RuntimeException {

    public InvalidTransactionTypeException(String message) {
        super(message);
    }
}