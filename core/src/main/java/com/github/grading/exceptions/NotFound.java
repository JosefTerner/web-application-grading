package com.github.grading.exceptions;

public class NotFound extends RuntimeException {

    public NotFound() {
    }

    public NotFound(String message) {
        super(message);
    }
}
