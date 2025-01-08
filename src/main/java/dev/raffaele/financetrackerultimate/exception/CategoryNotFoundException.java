package dev.raffaele.financetrackerultimate.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("Category not found!");
    }
}
