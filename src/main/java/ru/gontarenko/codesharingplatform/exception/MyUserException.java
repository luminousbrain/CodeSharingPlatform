package ru.gontarenko.codesharingplatform.exception;

public class MyUserException extends RuntimeException {
    public MyUserException(String message) {
        super(message);
    }
}
