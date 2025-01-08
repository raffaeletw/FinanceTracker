package dev.raffaele.financetrackerultimate.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException() {
        super("Transaction not found!");
    }
}
