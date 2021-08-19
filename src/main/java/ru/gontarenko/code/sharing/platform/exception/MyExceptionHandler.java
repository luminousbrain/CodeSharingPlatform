package ru.gontarenko.code.sharing.platform.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class MyExceptionHandler {

    @ExceptionHandler(value = { SnippetException.class })
    public String snippetExceptionHandle(SnippetException e) {
        String message = e.getMessage();
        if (message.equals("snippet was expired")) {
            return "snippet/snippet_expired";
        }
        return "not_found";
    }

    @ExceptionHandler(value = { UserException.class })
    public String myUserExceptionHandle(UserException e) {
        return "not_found";
    }
}
